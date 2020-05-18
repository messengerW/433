# 433
#### 项目环境

```
JDK:	1.8
compileSdkVersion:	28
minSdkVersion:	16
targetSdkVersion:	28
Gradle 插件版本:	3.4.1
Gradle 版本:	5.1.1
```



#### 数据库相关

```
App 用的是 SQLite 数据库，数据库文件： 433\app\src\main\assets\ftt.db
数据库中只保存了赛程信息和积分榜信息，没有用户信息表。
```



#### 第三方模块集成

```
zxinglibrary 这个模块是集成的二维码扫描模块，实现二维码扫描/生成的功能（与App主要功能无联系）。
```



#### 其他

```
App所有页面及功能都在 AndroidManifest.xml 清单文件中有注释。

MainActivity: 主页面相当于一个容器，“首页”、“比赛”、“分析”三个 Fragment 根据底部导航栏的切换依次
在主页面展示；
Fragment1: 对应底部导航栏的“首页”，主要包括2个组件：图片轮播、滑动新闻；
Fragment2: 对应底部导航栏的“比赛”，这里又嵌套了三个 Fragment；
Fragment3: 对应底部导航栏的“分析”；
LoginActivity、SettingsActivity、UserinfoActivity 分别是登录、设置、个人信息页面；
AnalysisActivity：分析界面；
GameActivity：展示页面，绘制了很多图表；
FavActivity：收藏页面；
Util文件夹里面是一些轮子，连接数据库的DBHelper，和一些顶部导航栏用到的类
```



#### 备注

```
团队名称：433足球数据分析小组
团队成员：王子鑫、吴昶昱、朱政凯
项目GitHub地址:	https://github.com/messengerW/433 
```