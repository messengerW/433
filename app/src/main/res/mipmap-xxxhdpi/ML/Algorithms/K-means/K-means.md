#  K-means 算法

### 0. 有监督/无监督学习

**有监督学习：**训练集有明确答案，监督学习就是寻找问题（又称输入、特征、自变量）与答案（又称输出、目标、因变量）之间关系的学习方式。监督学习模型分为**分类**和**回归**两类。

- 分类模型：目标变量是离散的分类型变量；
- 回归模型：目标变量是连续性数值型变量。

**无监督学习：**只有数据，无明确答案，即训练集没有标签。常见的无监督学习算法有聚类(clustering)，由计算机自己找出规律，把有相似属性的样本放在一组，每个组也称为簇（cluster）。

---

### 1. 什么是聚类

聚类是指在数据中发现数据对象之间的关系，将数据进行分组，组内的相似性越大，组间的差别越大，则聚类效果越好。

**聚类和分类的区别：**聚类是无监督的学习算法，分类是有监督的学习算法。聚类算法是没有标签的，聚类的时候，我们并不关心某一类是什么，我们需要实现的目标只是把相似的东西聚到一起；而分类是有已知标签的训练集（也就是说提前知道训练集里的数据属于哪个类别）的，分类算法在训练集上学习到相应的参数，构建模型，然后应用到测试集上。

----

### 2. K-means 算法原理及优缺点

**算法原理：**对于给定的样本集，按照样本之间的距离大小，将样本集划分为K个簇。让簇内的点尽量紧密的连在一起，而让簇间的距离尽量的大。

**优点：**

- 原理简单，容易实现，调参方便；
- 聚类效果优，收敛速度快，面对大数据集时**伸缩性^1^**较好；

**缺点：**

- 对**k值**和**初始随机质心**的选取十分敏感，容易陷入**局部最优解**；
- 对非凸数据集比较难收敛；
- 受离群点、噪声数据影响较大，因为算法是基于**均值**的；

**算法复杂度：**O(tkn)，其中 n 是样本总个数，k 是簇的个数，t 是迭代次数



<img src=".\K-means_原理.jpg" style="zoom: 50%;" />

[1]伸缩性：在数据集较大时，依然能够保持处理小数据集时的优越效果。

---

### 3. K-means 算法步骤

​	①首先给定一个 k 值，即要把样本聚为 k 类，然后**随机**选取 k 个样本点作为**初始聚类中心**；

​	②对于数据集中的每一个样本点，计算它到这 k 个聚类中心的距离，并把它分到**距离最小**的类簇中；

​	③每个类簇中已有若干样本点，重新计算每个类簇的中心点，作为下一次**迭代**的聚类中心；

​	④重复进行②③步的迭代，不断更新聚类中心直至收敛（聚类中心不再明显改变或达到指定的迭代次数）。

<img src=".\K-means_演示.gif" style="zoom:80%;" />

---

### 4. K-means 算法的问题与改进

- **问题一：k 值的选取**

  K-means 算法的聚类效果对 k 值的选取十分敏感（可通过调用 sklearn.metrics 的 calinski_harabaz_score 方法对聚类效果进行评估），针对于这一点的改进常见的有：

  **1）手肘法：**

  手肘法依据依据的指标是 **SSE**（Sum of the Squared Errors，**误差平方和**，又称和方差）：

  <img src=".\K-means_SSE.png" style="zoom:50%;" />

  手肘法确定 k 值的策略：

  - 当 k 值小于样本的真实簇数 R 时，k 每增大一个单位都会大幅度增加每个类簇的聚合程度，同时SSE大幅下降；
  - 当 k 接近 R 时，再增加 k 所得到的聚合程度回报会迅速减小，SSE下降幅度也会降低；
  - 随着 k 继续增大，SSE的变化趋于平缓。

  代码实现

  ```python
  # 手肘法确定 k 值
  import pandas as pd
  import matplotlib.pyplot as plt
  from sklearn.cluster import KMeans
  
  dataframe = pd.read_csv("C:\\Users\\mushr\\Desktop\\433\\England18-19.csv",encoding = 'GBK',engine='python')
  
  SSE = []
  for k in range(1,10):
      estimator = KMeans(n_clusters=k)
      estimator.fit(dataframe[['rate']])
      SSE.append(estimator.inertia_)
  X = range(1,10)
  plt.xlabel('clusters: k',)
  plt.ylabel('SSE')
plt.plot(X, SSE, 'o-')
  plt.show()
  ```

  

  

<img src=".\K-means_手肘.png" style="zoom: 61.8%;" />

根据手肘法则，就上图而言 k值 取 2 或者 3 是比较合理的。



**2）轮廓系数法**

依据的核心指标是**轮廓系数（Silhouette Coefficient）**，某个样本点 X~i~ 的轮廓系数定义为：

<img src=".\K-means_轮廓系数定义式.png" style="zoom:80%;" />

其中，a 是 X~i~ 与同簇的其他样本的平均距离，称为凝聚度，b 是 X~i~ 与最近簇中所有样本的平均距离，称为分离度。而最近簇的定义为：

<img src=".\K-means_轮廓系数最近簇.png" style="zoom:80%;" />

其中 p 是某个簇 C~k~ 中的样本。事实上，简单点讲，就是用 X~i~ 到某个簇所有样本平均距离作为衡量该点到该簇的距离后，选择离 X~i~ 最近的一个簇作为最近簇。

求出所有样本的轮廓系数后再求平均值就得到了**平均轮廓系数**。平均轮廓系数的取值范围为[-1,1]，且簇内样本的距离越近，簇间样本距离越远，平均轮廓系数越大，聚类效果越好。那么，很自然地，平均轮廓系数最大的k便是最佳聚类数。

  代码实现

  ```python
# 轮廓系数法确定 k 值
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.metrics import silhouette_score
  
dataframe = pd.read_csv("C:\\Users\\mushr\\Desktop\\433\\England18-19.csv",encoding = 'GBK',engine='python')
  
Scores = []
for k in range(2,10):
	estimator = KMeans(n_clusters=k)
    estimator.fit(dataframe[['rate']])
    Scores.append(silhouette_score(df_features[['rate']], estimator.labels_, metric='euclidean'))
X = range(2,10)
plt.xlabel('k')
plt.ylabel('silhouette_score')
plt.plot(X, Scores, '-o')
plt.show()
  ```

  

  <img src=".\K-means_轮廓系数.png" style="zoom: 67%;" />



- **问题二：初始聚类中心的选取**

  k 个随机的初始聚类中心点对 k-means 算法最终的聚类结果有很大的影响，除此之外 K-means 算法中有两个地方降低了 SSE：

  - 把样本点分到最近邻的簇中，这样会降低SSE的值；

  - 重新优化聚类中心点，进一步的减小了SSE。

  如此进行重复的迭代，最终得到的有可能是**局部最优解（局部最小的SSE）**，要想得到全局最优解需要选取合理的初始聚类中心。那么改如何选取合理的初始聚类中心呢?

  针对于**随机初始聚类中心**的改进算法 **K-means++：**

  ​	①从所有样本点中随机选取一个点 P~1~  作为第一个类簇的聚类中心；

  ​	②对于数据集中的每一个样本点 P~x~ ，计算 P~x~ 与所有已选定的聚类中心的距离的最小值 D~x~；

  ​	③在确定下一个类簇的聚类中心时，D~x~ 值更大的样本点被选中的概率更大；

  ​	④重复 ② ③ 两步直至确定出 k 个聚类中心；

  ​	⑤依据这 k 个聚类中心运行 K-means 算法。

  K-means++ 算法对初始聚类中心的选取进行优化，将随即选取策略改进为更合理的选取策略，得出的初始聚类中心**更加分散**，能够有效的**减少算法迭代次数**，加快运算速度，但仍旧无法解决**离群点问题**。

---

### 5. K-means 算法思路及实现

​	**思路**

>创建 k 个点作为起始质心（随机选择）
>
>当任意一个点的簇分配结果发生改变时
>
>​		对数据集中的每个数据点
>
>​				对每个质心
>
>​						计算质心与数据点之间的距离
>
>​				将数据点分配到距其最近的簇
>
>​		对每个簇，计算簇中所有点的均值并将均值作为质心



​	**实现一：调用 sklearn 库中的 KMeans 方法**

```python
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.datasets.samples_generator import make_blobs

data, target = make_blobs(n_samples=1000, n_features=2, centers=[[-2,-2],[0,0],[1,1],[2,2]], cluster_std=[0.5,0.2,0.3,0.3], random_state=9)
y_pred = KMeans(n_clusters=4, random_state=9).fit_predict(data)
plt.scatter(data[:, 0], data[:, 1], c=y_pred)
plt.show()
```

​	

​	**实现二：代码**

```python
from numpy import *
import matplotlib.pyplot as plt

def loadDataSet(fileName):
    dataMat = []
    fr = open(fileName)
    for line in fr.readlines():
        # 简单处理，删除每一行首尾的空格并以 \t 分隔
        currtLine = line.strip().split('\t')
        # 浮点化
        fltLine = list(map(float, currtLine))
        dataMat.append(fltLine)
    return dataMat


def distEcludean(vecA, vecB):
    return sqrt(sum(power(vecA - vecB, 2)))


def randCent(dataSet, k):
    n = shape(dataSet)[1]
    centroids = mat(zeros((k, n)))
    for j in range(n):
        minJ = min(dataSet[:, j])
        rangeJ = float(max(dataSet[:, j]) - minJ)
        centroids[:, j] = minJ + rangeJ * random.rand(k, 1)
    return centroids


def KMeans(dataSet, k, distMeas=distEcludean, createCent=randCent):
    m = shape(dataSet)[0]
    clusterAssment = mat(zeros((m, 2)))
    centroids = createCent(dataSet, k)
    clusterChanged = True
    while clusterChanged:
        clusterChanged = False
        for i in range(m):
            minDist = inf
            minIndex = -1
            for j in range(k):
                distJI = distMeas(centroids[j, :], dataSet[i, :])
                if distJI < minDist:
                    minDist = distJI
                    minIndex = j
            if clusterAssment[i, 0] != minIndex: clusterChanged = True
            clusterAssment[i, :] = minIndex, minDist ** 2
        # print(centroids,end='\n\n')
        for cent in range(k):
            ptsInClust = dataSet[nonzero(clusterAssment[:, 0].A == cent)[0]]
            centroids[cent, :] = mean(ptsInClust, axis=0)
    return centroids, clusterAssment


def showCluster(dataSet, k, centroids, clusterAssment):
    numSamples, dim = dataSet.shape
    if dim != 2:
        print
        "Sorry! I can not draw because the dimension of your data is not 2!"
        return 1

    mark = ['or', 'ob', 'og', 'ok', '^r', '+r', 'sr', 'dr', '<r', 'pr']
    if k > len(mark):
        print
        "Sorry! Your k is too large! please contact Zouxy"
        return 1

    # draw all samples
    for i in range(numSamples):
        markIndex = int(clusterAssment[i, 0])
        plt.plot(dataSet[i, 0], dataSet[i, 1], mark[markIndex])

    mark = ['Dr', 'Db', 'Dg', 'Dk', '^b', '+b', 'sb', 'db', '<b', 'pb']
    # draw the centroids
    for i in range(k):
        plt.plot(centroids[i, 0], centroids[i, 1], '+', markersize=12)

    plt.show()


k = 4
daMat = mat(loadDataSet('testSet.txt'))
myCentroids, clustAssing = KMeans(daMat, k)
showCluster(daMat, k, myCentroids, clustAssing)
```





---

*引用及参考*

[1]: 《机器学习实战》	"[美] Peter Harrington"
[2]: https://blog.csdn.net/u013719780/article/details/51755124	"机器学习系列：（六）K-Means聚类"
[3]: https://zhuanlan.zhihu.com/p/75477709	"用人话讲明白快速聚类kmeans"
[4]:https://www.cnblogs.com/lliuye/p/9144312.html	"kmeans算法理解及代码实现"