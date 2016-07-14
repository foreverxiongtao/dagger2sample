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

准备工作完了，我们先定义了一个全局的GlobalApplication，看一下具体的代码:

    public class GlobalApplication extends Application {
	/***获取全局模块主要用于提供context,config等**/
    private APPComponent mAppComponent;
    /****
     * 获取全局Context
     */
    private static GlobalApplication mGlobalApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局的连接器
        mAppComponent = APPComponent.Initializer.init(this);
        mGlobalApplication = this;
    }


    public static GlobalApplication getGlobalApplication() {
        return mGlobalApplication;
    }

    public APPComponent getAppComponent() {
        return mAppComponent;
    }
	}

细心的朋友会发现，我们平时可以直接获取application实例，为啥还要用这个appComponent东东来提供呢，这不是简单的问题复杂化吗？？？？如果你有这样的想法，很正常，其实我们这里的appcomponent就类似一个厨房，我们可以从厨房取出我们想要吃的菜，而我们不用关心这个菜是谁做的，怎么做的，我们只负责吃就够了(原谅我吃货的本性)。那我们继续看一下appcomponent类。

	@Singleton
	@Component(modules ={APIModule.class, APPModule.class})
	public interface APPComponent {
    final class Initializer {
        private Initializer() {
            //空实现
        }

        /***
         * 获取连接器实例
         *
         * @param _application
         * @return
         */
        public static APPComponent init(GlobalApplication _application) {
            return DaggerAPPComponent.builder().aPPModule(new APPModule(_application)).build();
        }
    }

    RemoteAPI getRemoteAPI();
	} 
其实appcomponent是一个接口，接口用了一个@Component注解来表明是注入器,其中的modules来表明，依赖来源于APIModule或者APPModule类，比如RemoteAPI，我们猜测应该是在APIModule或者APPModule中肯定提供了。
我们列出APPModule,APIModule这两个类的代码。
####APIModule.class####

    @Module
	public class APIModule {
    /**
     * 服务器请求地址基类
     */
    public static final String BASE_URL = "http://op.juhe.cn/onebox/";


    /***
     * 获取okhttpclient实例主要可以用于不同页面进行网络拦截设置，或者读取，写入超时d单独设置
     *
     * @return
     */
    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
    }

    /***
     * 创建retrofit实例，暴露remoteapi实例，供外部需要注入的对象进行调用
     *
     * @param _client
     * @return
     */
    @Singleton
    @Provides
    public RemoteAPI provideRemoteAPI(OkHttpClient _client) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(_client).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RemoteAPI.class);
    }
	}
####APIModule.class####
    @Module
	public class APPModule {
    private GlobalApplication mApplication;

    public APPModule(GlobalApplication _application) {
        this.mApplication = _application;
    }

    @Singleton
    @Provides
    public GlobalApplication provideApplication() {
        return mApplication;
    }
	}

我们可以发现APIModule这个类主要提供了一些关于Retrofit2的连接，读取 写入超时,以及对象解析器的配置，其中里面我们发现了provideRemoteAPI这个方法,请注意它的写法，@Provides注解,表明如果外部需要依赖时，Dagger就知道可以通过该方法来获取依赖的实例。APPComponent中的 RemoteAPI getRemoteAPI();其实他的提供者就是APIMODule中的provideRemoteAPI。讲到这里，你可能有点蒙了，怎么你没有写代码，dagger就知道自己调用呢，没错，就是这样，细心的你又会发现APPComponent中的

    public static APPComponent init(GlobalApplication _application) {
            return DaggerAPPComponent.builder().aPPModule(new APPModule(_application)).build();
        }
这段代码中的DaggerAppComponent这个对象，其实如果写完代码不编译，发现根本没有这个对象，那么他是怎么生成的呢,那么你还记得我们前面所说的dagger2解决问题的思想就是:利用生成和写的代码混合达到看似所有的产生和提供依赖的代码都是手写的样子,对，你也许明白了，DaggerAppComponent就是apt这个工具自动生成的，就是这么神奇，
可能你会说，我为了要一个RemoteAPI,Application对象，而弄了这么复杂的一坨东西,太复杂了。
那么我们继续高级的用法。
现在我们实现一个简单的功能
请看主页面布局![主页面功能](http://i.imgur.com/85QuMMk.png)
我们点击获取数据按钮，则会调取天气接口，获取天气相关的数据，并在textview上显示,功能是不是很简单
明白需求后，我们就定义数据请求接口如下:

     /****
     * 获取天气信息的接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("weather/query")
    Observable<WeatherInfo> queryweather(@FieldMap Map<String, String> params);

这里返回一个Observable对象，不熟悉Rxjava的，赶紧去恶补一下。接下来我们查看一下mvp中的m数据层，
####MainModule.class####
    public class MainModel extends BaseModel {
    public MainModel(RemoteAPI _remoteAPI) {
        super(_remoteAPI);
    }

    /***
     * 获取学生信息
     *
     * @param params 请求参数
     * @return
     */
    public Observable<WeatherInfo> getstudent(Map<String, String> params) {
        return mRemoteAPI.queryweather(params);
    }
	}
这个类中我们获取了Observable对象，而数据就存储在这个对象中，接下来我们再查看mvp结构中的presenter层
####MainActivityPresenter.class####

	public class MainPresenter extends BasePresenter<MainView, MainModel> {
    public MainPresenter(RemoteAPI _remoteAPI) {
        super(_remoteAPI);
    }

    @Override
    public MainModel setUpModel() {
        return new MainModel(getRemoteAPI());
    }

    public void updateData(String cityname) {
        Subscriber<WeatherInfo> subscribe = new Subscriber<WeatherInfo>() {
            @Override
            public void onCompleted() {
                getView().dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().showErrorMsg("网络异常");
            }

            @Override
            public void onNext(WeatherInfo _weatherInfo) {
                getView().updateData(_weatherInfo);
            }
        };
        Observable<WeatherInfo> localWeatherInfoObservable = getModel().getstudent(NetParams.getInstance().queryWeather(cityname));
        localWeatherInfoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                getView().showLoadingDialog("加载中");
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(subscribe);
        addSubscrib(subscribe);
    }
	}

其实这个类中的代码也挺简单的，也就是利用的Rxjava异步将数据传给View层，以及在这个过程中调用View层的一些加载情况的处理。正常情况下通过getView().updateData(_weatherInfo);将数据回传给View，也就是对应的MainActivity,具体的我们看一下代码
####MainActivityPresenter.class####

    public class MainActivity extends BaseActivity implements MainView {
    @Inject
    MainPresenter mMainPresenter;
    private TextView mTv_main_weather_desc;
    private Button btn_main_querydata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    protected void initView() {
        mTv_main_weather_desc = (TextView) findViewById(R.id.tv_main_weather_desc);
        btn_main_querydata = (Button) findViewById(R.id.btn_main_querydata);
        btn_main_querydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.updateData("北京");
            }
        });
    }

    @Override
    protected void initcustomerActivityComponent(APPComponent _appcomponent) {
        DaggerMainActivityComponent.builder().aPPComponent(_appcomponent).mainActivityModule(new MainActivityModule(this)).build().inject(this);
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return mMainPresenter;
    }

    @Override
    public void refresh() {

    }



    @Override
    public void loadMore() {

    }

    @Override
    public void updateData(WeatherInfo _weatherInfo) {
        if (_weatherInfo != null) {
            mTv_main_weather_desc.setText(_weatherInfo.getResult().getData().getRealtime().getCity_name()+":"+_weatherInfo.getResult().getData().getRealtime().getDataUptime());
        }
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showEmptyMsg(String msg) {

    }
	}

其实这个mainActivity，也就是mvp中的view层和我们猜想的一样，其实这个类也没有什么价值,核心的代码就两句

    @Override
	protected void initcustomerActivityComponent(APPComponent _appcomponent) {
    DaggerMainActivityComponent.builder().aPPComponent(_appcomponent).mainActivityModule(new MainActivityModule(this)).build().inject(this);
	}
这里的主要功能就是通过MainAppComponent注入器将我们需要的MainModulle提供的实例注入到MainActivity中,这样我们@Injec MainPresenter mMainPresenter 时，dagger就会去对应的mainModule中去找对应的实例去生成我们需要的mMainPresenter实例。

     @Inject
    MainPresenter mMainPresenter;


接下来我们来查看看
####MainActivityComponent.class####

    @ActivityScope
	@Component(dependencies = APPComponent.class, modules = MainActivityModule.class)
	public interface MainActivityComponent {
    /**将MainActivityMoudule提供的实体注入到maintivity中**/
    void inject(MainActivity _mainactivity);
	}

MainActivityComponent暴露了一个方法，就是将MainActivityModule提供的依赖和MainActivity连接起来，并且当前的MainActivityCoponent连接器还依赖AppComponent连接器，也就是说AppComponent连接器也提供了MainModule中需要的实例，那到底是哪个对象，我们看一下MainModule对象，就很明显了,代码如下:
####MainActivityModule.class####


	@Module
	public class MainActivityModule {
    private MainActivity mMainActivity;

    public MainActivityModule(MainActivity _mainactivity) {
        this.mMainActivity = _mainactivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mMainActivity;
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainActivity _mainactivity, RemoteAPI _remoteApi) {
        MainPresenter tempMainPresenter = new MainPresenter(_remoteApi);
        tempMainPresenter.attachView(_mainactivity);
        return tempMainPresenter;
    }
	}


通过以上的代码其实核心的代码就在最后的一个方法

	 @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainActivity _mainactivity, RemoteAPI _remoteApi) {
        MainPresenter tempMainPresenter = new MainPresenter(_remoteApi);
        tempMainPresenter.attachView(_mainactivity);
        return tempMainPresenter;
    }
	}
这个方法提供了MainActivity中我们苦等的mainpresenter,重点来了,再重复一次，重点，provideMainPresenter这个方法的两个参数MainActivity和RemoteApi这两个参数也必须是@Provides方法提供的，否则编译就会报错，聪明的你是不是发现了其实 

	@Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mMainActivity;
    }
provideMainActivity这个方法就提供了MainActivity了，原来provideMainPresenter参数中的MainActivity 就是前者提供的，好吧，这尼玛也太奇怪了，那么RemoteAPI呢，又是谁提供的呢。
好吧我又要继续啰嗦了,请大家执行看
![dependence](http://i.imgur.com/cTRtpFt.png)

我们在MainActivityComponent中依赖了APPComponent了，那么我再次移步APPComponent

![remote](http://i.imgur.com/wd9P9y4.png)

现在你是不是豁然开朗啊，原来这里MainActivityModule中的provideMainPresenter的RemoteAPI参数就是APPComponent通过getRemoteAPI()暴露的啊，现在所有的疑惑是不是都解决了。
顺便附上一下运行的结果。有图有真相，小程序一枚:
![text](http://i.imgur.com/VB3KRnf.gif)

有什么意见或建议的请发送邮件到992907013@qq.com

好啦，暂时就写到这里了，dagger2的一些高级用法，下次再补上了，尼玛，都晚上八点了，还在公司撸代码，回家吃饭了!!!!!!!


