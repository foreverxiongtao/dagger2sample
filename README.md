##Dagger2+MVP+Retrofit2+RxJava##
###前记###
其实本人以前是比较反感写博客之类，总觉得是浪费时间,后来和一个朋友交流的时候，他一句话点拨了我----"在我们谷歌百度资料的背后总有一群默默分享自己技术的大牛们，正是因为有他们,我们才有这么多的资源".顿时醍醐灌顶，本人也并非什么大牛，只是对新知识充满着好奇，抱着学习的心理，写下这篇文章，来加强对知识的理解，希望能和有兴趣的朋友一起交流,学习，进步。
###直入主题###
这篇文章主要是来介绍dagger2的，但是为啥还要介绍mvp,retrofit2,rxjava,好吧，我还是顺便带一下.
####mvp####
其实mvp就是一种用于实现解耦而出现的一种轻量级架构，源自于mvc,主要是让数据,业务，页面交互进行分离，有助于模块化，方便测试和维护。
####retrofit2####
开发过android的人我想大概都知道volley,xutils,asynchttpclient,之类的框架，其实这些框架在android开发早期都是各有优势,好坏不做区分，个人观点，但是伴随着square公司的okhttp问世，其他的几个网络框架就相形见绌了，以至于google在Google貌似在6.0版本里面删除了HttpClient相关API，而选择了okhttp,好坏大家自己掂量，后来square又开源了retrofit2，这货底层其实用的请求就是基于okhttp，只是封装了一下，代码更加优雅。逼格更高，好吧，扯远了.
####rxjava####
RxJava 是一个响应式编程框架，采用观察者设计模式.如果要细讲，估计一本书都无法说完。这里推荐一个网址[http://http://gank.io/post/560e15be2dca930e00da1083](http://http://gank.io/post/560e15be2dca930e00da1083 "Rxjava")，学习成本比较大，胆小者勿入，其实说白了也就是两个字"异步"，这货可以很轻松的切换线程，而不需要用android原生的asynctack，handler来发送消息进行线程间通信了.
说了半天废话，我想大家已经不耐烦了吧，好吧，我们改讲讲今天的主角吧dagger2......
####Dagger2####
Dagger2字面意思匕首(听着好像很牛逼的样子),其实就是一个android的依赖注入框架，依赖注入框架主要用于模块间解耦，提高代码的健壮性和可维护性，更有助于单元测试和模块化测试,它是square公司的开源项目----dragger(尼玛，又是square公司的)的一个分支。
Dagger1是在编译的时候实行绑定，不过也用到了反射机制。但这个反射不是用来实例化对象的，而是用于图的构成。Dagger会在运行的时候去检测是否一切都正常工作，所以使用的时候会付出一些代价：偶尔会无效和调试困难。
但是，但是，再次重复一遍,Dagger2主要由google接手公司研发的。解决问题的基本思想是：利用生成和写的代码混合达到看似所有的产生和提供依赖的代码都是手写的样子,所以我们不用担心dagger2所带来的性能上的影响了,放心使用吧。
写到这里，估计有人又会骂我了，什么鬼，说了等于白说，好吧，我承认语言表达能力有限。
####依赖####
什么是依赖，富二代就是一种典型的依赖，儿子依赖老子，好吧，又有人要骂我了，言归正传，如果在ClassSon中，有一个ClassFather的实例，则称ClassSon对ClassFather有一个依赖，代码如下：

    public class ClassSon{
	 ClassFather father;
	 public ClassSon(){
			father=new Father();
		}
	}
####注入依赖####
注入依赖就是非自己主动创建，也就是不用自己主动去new出对象，而是通过外部传入依赖，我想你已经猜到了:

    public class ClassSon{
	ClassFather father;
	public ClassSon(ClassFather _father){
			this.father=_father;	
		}
	}
细心的你可能会发现，这就是一个简单的传参而已啊，对，就是这个简单的动作，我们可以让classFather发挥其他的功能，倘若是用内部类的方式，那么他就只能被当前的classSon消费了，利用率太低，代码耦合太高
####why dagger2####
大家都知道mvp使用与解耦的，用过的人都知道我们在activity，也就是mvp中的view中需要new presenter的实例，按照我们上面的说话，这样presenter和view就没有实现完全的解耦，那么接下来就是我们dagger2商场的时间了，我们可以利用该DI(Dependence Inject)框架直接生成presenter的实例。代码如下:

    public class MainActivity extends BaseActivity implements MainView{
		@Inject
		MainPresenter presenter;
	}

这样我们就很轻松的得到了presenter的实例了，是不是很简单。
....@Inject又是个什么鬼，好吧，我又被打败了
上面只是我们队DI框架的一个基础的了解，为了配合下面的实例，我们有必要来了解几个Dagger2中几个角色
@Inject @Module @Component @Provides @Scope @SubComponent。

1. @Inject通常我们在需要依赖的地方就可以使用这个注解，简单点说。我们需要一个实例对象classFather,那么我们就可以加上@Inject这个注解，那么dagger就会给你创建一个实例满足你，需要啥就满足啥，狼友们有福了......
2. @Module:Module这个类中主要是用来提供依赖，因此我们就需要定义一个类，用@Module注解，这样Dagger在创建实例的时候就知道从那个类中去找我们需要的依赖。
3. @Provides:在module中，我们定义一个方法来生成一个对应的实例，那么我们就用这个注解，然后dagger就会根据这个方法来生成对应的实例。
4. @Component：Component就是一个注入器，连接器，也就是说也可以说是@Inject和@Module的桥梁，它的主要作用就是连接这两个部分。 Components可以提供所有定义了的类型的实例，比如：我们必须用@Component注解一个接口然后列出所有的　　 @Modules组成该组件，如 果缺失了任何一块都会在编译的时候报错。所有的组件都可以通过它的modules知道依赖的范围。
5. @Scope：我们可以通过dagger来自定义注解从而来赝鼎注解的作用域，比如和activity，application,fragment的生命周期进行绑定。
好吧，专业名词也介绍的差不多了，直接上实例吧.
首先查看一下实例项目的结构:
![项目结构](http://i.imgur.com/jXymMMa.png) 
###实战
由于dagger使用apt工具生成代码，那么我们需要在Project gradle中加入以下插件:
    
	buildscript {
    	repositories {
       	 jcenter()
    	}
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.0'
       classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
	}
		
接下来我们在app builde.gradle中加上以下代码

    apply plugin: 'com.android.application'
    apply plugin: 'com.neenbedankt.android-apt' // 注释处理
    android {
    compileSdkVersion 23
    buildToolsVersion "23.0.3"

    defaultConfig {
        applicationId "com.desperado.dagger2sample"
        minSdkVersion 15
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    }

    dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.4.0'

    //dagger2
    apt 'com.google.dagger:dagger-compiler:2.0.2' //指定注解处理器
    compile 'com.google.dagger:dagger:2.0.2' // dagger2
    compile 'com.google.dagger:dagger-compiler:2.0.2' // dagger2
    provided 'javax.annotation:jsr250-api:1.0' // Java标注


    compile 'io.reactivex:rxandroid:1.1.0' // RxAndroid
    compile 'io.reactivex:rxjava:1.1.0' // RxJava异步处理


    compile 'com.squareup.retrofit2:retrofit:2.0.0'   //网络请求类
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.0'
    compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta4' // Retrofit的gson库
	}


