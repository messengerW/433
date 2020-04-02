# 决策树算法实现

> 上一篇笔记介绍了决策树算法的基本概念、过程，这篇就介绍决策树的生成算法。老规矩，代码和直接调包



### 一、代码实现

代码参考的是《机器学习实战》，书中的决策树算法在选择最优属性的时候采用的是**熵**，也就是ID3算法。另外，书中的代码还有一个缺点——**只针对布尔型**特征值，每个特征属性的取值必须是 0/1 或者 是/不是，否则就没法应用，比如说我们这次实验用的数据集，属性的取值都是数值型，我用书中的这份代码跑了一下，只用了一个属性就结束了。也就是说这份代码只是帮助理解决策树构建过程、原理，实际应用价值不大。

###### ① 导入要用到的包包

```python
import operator
import numpy as np
import pandas as pd
from math import log
from scipy.io import arff
# 这个是绘制树所需要的函数,视情况导入
# from Test.Course import decisionTreePlot as dtPlot
```



###### ② 生成数据集

```python
def createDataset():
    # 前两项为特征值,最后一项为类标签（即样本所属的类别）
    dataSet = [[1, 1, 'yes'], [1, 1, 'yes'], [1, 0, 'no'], [0, 1, 'no'], [0, 1, 'no']]
    # 特征名,features name
    labels = ['a', 'b']
    return dataSet, labels
```



###### ③ 计算香农熵

```python
def calcShannonEntropy(dataSet):
    # 先计算样本总个数
    numEntries = len(dataSet)
    #
    labelCounts = {}
    # 遍历每一条样本，统计数据集中所有样本的类别
    for data in dataSet:
        currentLabel = data[-1]
        if currentLabel not in labelCounts.keys():
            labelCounts[currentLabel] = 0
        labelCounts[currentLabel] += 1
    # 初始化香农熵
    shannonEntropy = 0.0
    # 计算香农熵
    for key in labelCounts:
        prob = float(labelCounts[key]) / numEntries
        shannonEntropy -= prob * log(prob, 2)
    return shannonEntropy
```



###### ④ 切分整合数据集

```python
def splitDataSet(dataSet, axis, value):
    """
    @Function: 每一次挑选一个特征属性划分数据集(生成分支)后,都需要把数据集中对应的列删除。
    :param dataSet: 数据集
    :param axis: 列索引
    :param value: 列值
    :return: 
    """
    retDataSet = []
    for featVec in dataSet:
        if featVec[axis] == value:
            # 这两步的作用是获取剔除 axis 列属性后的特征向量
            reducedFeatVec = featVec[:axis]
            reducedFeatVec.extend(featVec[axis + 1:])
            # 添加至列表末尾
            retDataSet.append(reducedFeatVec)
    return retDataSet
```



###### ⑤ 选择最优属性

```python
def chooseBestFeature(dataSet):
    """
    @Function: 每次创建新分支时，根据一定的度量方式(信息增益、信息增益率、基尼指数)选择最优属性
    :param dataSet: 待划分的数据集
    :return: 最优特征的索引值
    """
    # 得到特征的个数,-1是因为每条样本的最后一项是所属标签而不是特征属性
    numFeatures = len(dataSet[0]) - 1
    # 计算当前熵
    baseEntropy = calcShannonEntropy(dataSet)
    # 初始化最高信息增益、最优特征
    bestInfoGain = 0.0
    bestFeature = -1

    # 遍历所有特征
    for i in range(numFeatures):
        # 将数据集中所有第 i 个特征存入特征值列表
        featList = [example[i] for example in dataSet]
        # 对上一步得到的特征值列表进行去重
        uniqueVals = set(featList)
        # 数据集依据当前特征划分后的新熵值
        newEntropy = 0.0

        # 遍历已去重的特征值列表
        for value in uniqueVals:
            # 按照当前 特征(i) 及其 特征值(value) 对数据集进行划分
            # 得到的subDataSet就是数据集中所有当前特征的取值为value( feature(i) = value )的样本集合
            subDataSet = splitDataSet(dataSet, i, value)
            # 权重, 数据集中第i个特征取值为value的样本占样本总体的比例,即出现的频率
            prob = len(subDataSet) / float(len(dataSet))
            # 累加求得最终的新熵
            newEntropy += prob * calcShannonEntropy(subDataSet)

        # 信息增益 = 划分前的熵 - 划分后的熵,可理解为数据集混乱度的减少程度
        infoGain = baseEntropy - newEntropy
        # 用当前特征的信息增益与最高信息增益作比较,更新最高信息增益、最优特征
        if (infoGain > bestInfoGain):
            bestInfoGain = infoGain
            bestFeature = i

    return bestFeature
```



###### ⑥ 当继续创建新分支时发现属性已用完，需要对分支下的样本做多数表决投票

```python
def majorityCnt(classList):
    classCount = {}
    for vote in classList:
        if vote not in classCount.keys():
            classCount[vote] = 0

    sortedClassCount = sorted(classCount.iteritems(), key=operator.itemgetter(1), reverse=True)
    return sortedClassCount[0][0]
```



###### ⑦ 生成决策树

```python
def createTree(dataSet, labels):
    """
    递归创建决策树
    :param dataSet: 数据集
    :param labels: 标签列表
    :return:
    """
    # 首先获得数据集中每一条样本所属的标签类,存入classList列表
    classList = [example[-1] for example in dataSet]
    # 讨论递归函数终止的两种情况(满足其一即可跳出递归):
    # condition-1: 标签列表中所有标签完全相同,即所有样本属于同一个类别
    if classList.count(classList[0]) == len(classList):
        return classList[0]
    # condition-2: 所有特征都被使用完了
    if len(dataSet[0]) == 1:
        return majorityCnt(classList)
    # 从当前数据集中寻找最优特征
    bestFeat = chooseBestFeature(dataSet)
    # 最优特征对应的特征名映射
    bestFeatLabel = labels[bestFeat]
    # 创建 myTree 字典存储树的所有信息
    myTree = {bestFeatLabel: {}}
    # 从标签列表中删除当前最优特征对应的标签名
    del labels[bestFeat]
    # 本次生成新的分支所选用的最优特征索引值:bestFeat,遍历数据集得到每条样本的最优特征值
    featValues = [example[bestFeat] for example in dataSet]
    # 去重
    uniqueVals = set(featValues)
    # 遍历去重后的最优特征值列表
    for value in uniqueVals:
        # 为了保证每次调用createTree()时不改变原列表的内容(del),创建一个labels的副本
        subLabels = labels[:]
        # 依据最优特征及其特征取值划分后的数据集
        splitedDataSet = splitDataSet(dataSet, bestFeat, value)
        # 在每个数据集划分上递归调用createTree()函数
        myTree[bestFeatLabel][value] = createTree(splitedDataSet, subLabels)

    return myTree
```



###### 测试

```python
def test1():
    """
    Use dataset1: CM1.arff
    :return:
    """
    # 加载数据集文件
    file = '../Test/Data/CM1.arff'
    # 解析文件
    data = arff.loadarff(file)
    # 获取 dataframe 格式的数据集
    df = pd.DataFrame(data[0])
    # 将数据集格式转为 二维列表
    datalist = df.values.tolist()
    # 获取 labels 列表
    featlabels = df.columns.values.tolist()

    # kf = KFold(n_splits=10, shuffle=False, random_state=50)
    # for train_index, test_index in kf.split(df):
    #     print('train', len(train_index), 'test', len(test_index))

    myTree = createTree(datalist, featlabels)
    dtPlot.createPlot(myTree)

    print(myTree)

if __name__ == "__main__":
    # test0()
    test1()
```



### 二、sklearn

```python
# 使用wine数据集做测试
import graphviz		# 绘图
import pandas as pd
from sklearn import tree
from sklearn.datasets import load_wine		# 数据集
from sklearn.model_selection import train_test_split	# 切分数据集

wine = load_wine()

# 所有特征属性
X = pd.DataFrame(wine.data)
# 所有样本的类标签
y = pd.DataFrame(wine.target)

# 将数据集划分成训练集、测试集,测试集占比0.2
xtrain, xtest, ytrain, ytest = train_test_split(wine.data, wine.target, test_size=0.2)

# 分类器
clf = tree.DecisionTreeClassifier(criterion="entropy",
                                  splitter="best",
                                  random_state=10,
                                  max_depth=3,
                                  min_samples_leaf=10,
                                  min_samples_split=10
                                 )
# 拟合模型
clf.fit(xtrain, ytrain)
# 计算模型预测准确率
score = clf.score(xtrain, ytrain)
#print(score)

# 绘制决策树
featureNames = wine.feature_names
# 特征名：['酒精','苹果酸','灰','灰的碱性','镁','总酚','类黄酮','非黄烷类酚类','花青素','颜色强度','色调','od280/od315稀释葡萄酒','脯氨酸']
classNames = wine.target_names
# 类别：["琴酒","雪莉","贝尔摩德"]

wine_dot = tree.export_graphviz(clf,feature_names=featureNames,
                                class_names=classNames,
                                filled=True,
                                rounded=True
                               )
graph = graphviz.Source(wine_dot)
graph
```



**DecisionTreeClassifier() 调参**

| 参数                                                   | 解释                                                         |
| :----------------------------------------------------- | :----------------------------------------------------------- |
| criterion<br />特征选择标准                            | 可以使用"gini"或者"entropy"，前者代表基尼系数，后者代表信息增益。一般说使用默认的基尼系数"gini"就可以了，即CART算法。除非你更喜欢类似ID3, C4.5的最优特征选择方法。 |
| splitter<br />特征划分点选择标准                       | 可以使用"best"或者"random"。前者在特征的所有划分点中找出最优的划分点。后者是随机的在部分划分点中找局部最优的划分点。默认的"best"适合样本量不大的时候，而如果样本数据量非常大，此时决策树构建推荐"random" |
| max_features<br />划分时考虑的最大特征数               | 可以使用很多种类型的值，默认是"None",意味着划分时考虑所有的特征数；如果是"log2"意味着划分时最多考虑log2N个特征；如果是"sqrt"或者"auto"意味着划分时最多考虑N−−√个特征。如果是整数，代表考虑的特征绝对数。如果是浮点数，代表考虑特征百分比，即考虑（百分比xN）取整后的特征数。其中N为样本总特征数。<br/>一般来说，如果样本特征数不多，比如小于50，我们用默认的"None"就可以了，如果特征数非常多，我们可以灵活使用刚才描述的其他取值来控制划分时考虑的最大特征数，以控制决策树的生成时间。 |
| max_depth<br />决策树最大深度                          | 决策树的最大深度，默认可以不输入，如果不输入的话，决策树在建立子树的时候不会限制子树的深度。一般来说，数据少或者特征少的时候可以不管这个值。如果模型样本量多，特征也多的情况下，推荐限制这个最大深度，具体的取值取决于数据的分布。常用的可以取值10-100之间。 |
| min_samples_split<br />内部节点再划分所需最小样本数    | 这个值限制了子树继续划分的条件，如果某节点的样本数少于min_samples_split，则不会继续再尝试选择最优特征来进行划分。 默认是2.如果样本量不大，不需要管这个值。如果样本量数量级非常大，则推荐增大这个值。有大概10万样本，建立决策树时，我选择了min_samples_split=10。可以作为参考。 |
| min_samples_leaf<br />叶子节点最少样本数               | 这个值限制了叶子节点最少的样本数，如果某叶子节点数目小于样本数，则会和兄弟节点一起被剪枝。 默认是1,可以输入最少的样本数的整数，或者最少样本数占样本总数的百分比。如果样本量不大，不需要管这个值。如果样本量数量级非常大，则推荐增大这个值。之前的10万样本项目使用min_samples_leaf的值为5，仅供参考。 |
| min_weight_fraction_leaf<br />叶子节点最小的样本权重和 | 这个值限制了叶子节点所有样本权重和的最小值，如果小于这个值，则会和兄弟节点一起被剪枝。 默认是0，就是不考虑权重问题。一般来说，如果我们有较多样本有缺失值，或者分类树样本的分布类别偏差很大，就会引入样本权重，这时我们就要注意这个值了。 |
| max_leaf_nodes<br />最大叶子节点数                     | 通过限制最大叶子节点数，可以防止过拟合，默认是"None”，即不限制最大的叶子节点数。如果加了限制，算法会建立在最大叶子节点数内最优的决策树。如果特征不多，可以不考虑这个值，但是如果特征分成多的话，可以加以限制，具体的值可以通过交叉验证得到。 |
| class_weight<br />类别权重                             | 指定样本各类别的的权重，主要是为了防止训练集某些类别的样本过多，导致训练的决策树过于偏向这些类别。这里可以自己指定各个样本的权重，或者用“balanced”，如果使用“balanced”，则算法会自己计算权重，样本量少的类别所对应的样本权重会高。当然，如果你的样本类别分布没有明显的偏倚，则可以不管这个参数，选择默认的"None" |
| min_impurity_split<br />节点划分最小不纯度             | 这个值限制了决策树的增长，如果某节点的不纯度(基尼系数，信息增益，均方差，绝对差)小于这个阈值，则该节点不再生成子节点。即为叶子节点 |
| presort<br />数据是否预排序                            | 这个值是布尔值，默认是False不排序。一般来说，如果样本量少或者限制了一个深度很小的决策树，设置为true可以让划分点选择更加快，决策树建立的更加快。如果样本量太大的话，反而没有什么好处。问题是样本量少的时候，我速度本来就不慢。所以这个值一般懒得理它就可以了。 |

