# android知识点
## 一、android组件
### activity 知识点

* 生命周期

  > 1、`onCreate` 、`onRestart`、`onStart`、`onResume`、`onPause`、`onStop`、`onDestroy`
  >
  > 2、`onPause` ->`onSaveInstanceState`、`onStart`->`onRestoreInstanceState`

* 启动模式

  > 1、`standard  `    标准模式、创建实例位于栈顶，谁启动就位于谁的栈顶
  >
  > 2、`singleTop` 栈顶复用模式 启动时，位于栈顶，复用，调用`onNewIntent` ,否则创建
  >
  > 3、`singleTask` 栈内复用模式  启动时，栈内存在 复用，上面实例清除 调用`onNewIntent` 否则创建
  >
  > 4、`singleInstance` 单例模式 寻找任务栈，存在直接复用，不存在创建任务栈，并将`activity`放入其中.

* 启动流程


## activity面试题

* `Activity A `打开`Activity B` 按返回键的生命流程

  > A `onPause` -> B `onCreate` -> B `onStart`-> B `onResume` ->A `onStop` ->back ->B `onPause` -> A `onRestart` -> A `onStart` -> A `onResume` -> B `onStop` -> B `onDestroy`

* 生命周期

  > 1 、`startService/stopService ` : `onCreate`-> `onStartCommand`->`ondestroy`
  >
  > 2、`bindService/unbindService`: `onCreate`->`onBind`->`onUnBind`->`onDestroy`
  >
  > 3、混合 同时使用  `onCreate` 只会执行一次，只有解除绑定后 `stopService` 才会生效

* `intentService`

  * 生命周期 `onCreate` ->`onStartCommand`->`onHandleIntent` ->`onDestroy`
  * `onhandleIntent` 在子线程，并且任务执行完，则销毁。
  * 多次调用，耗时任务在 `onhandleIntent`中执行，第一个执行完，才执行第二个。

## service 面试题

## service 知识点

* 生命周期

  > 1 、`startService/stopService ` : `onCreate`-> `onStartCommand`->`ondestroy`
  >
  > 2、`bindService/unbindService`: `onCreate`->`onBind`->`onUnBind`->`onDestroy`
  >
  > 3、混合 同时使用  `onCreate` 只会执行一次，只有解除绑定后 `stopService` 才会生效

* `intentService`

  * 生命周期 `onCreate` ->`onStartCommand`->`onHandleIntent` ->`onDestroy`
  * `onhandleIntent` 在子线程，并且任务执行完，则销毁。
  * 多次调用，耗时任务在 `onhandleIntent`中执行，第一个执行完，才执行第二个。

## service 面试题

* `startService`多次start会什么反应？ 会多次调用 `onStartCommand`

* `bindService` 多次`bind`会有什么反应？ 传入的`serviceConnection`参数是之前绑定的  无反应。

* 远程服务的创建过程

  * `step1` 在对应的文件目录下创建 `aidl `文件
  * `step2` 创建一个`service` ，并在内部实现`aidl `的接口函数，`onBind `返回这个实例。
  * `step3` 在`Android androidManifest` 设置该 `service` 对应的属性
  * `step4` 在client 来`bindservice` ，并在`serviceConnecet` 回调参数中 获取`onBind `返回的实例，来与`remote`进行交互
  * 保活，1 两个服务相互监听， 2 在断开服务链接 时 重新绑定， 3 使用死亡代理，service 死掉后，重新绑定。

* `service` 与 `intentService` 的区别

  * * `service`

      >  1、无法处理耗时任务，除非开启一个线程
      >
      >  2、长时间运行在后台，除非调用`stop`

    * `intentService`

      > 1、开启后 在`onHandleIntent`执行耗时操作，不会阻塞应用程序的主线程。
      >
      > 2、执行完任务后，自动停止
      >
      > 3、多次启动，耗时操作会以工作队列的方式在`onHandleIntent`回调中执行，并且，每次只会执行一个工作线程。



## 广播

* 特点：1 收到广播，无进程，自动创建 2 应用必须被打开过，广播才被执行。 3 强行停止后，不会自己创建进程，除非用户自己手动打开界面。
* 无序广播与有序广播。
  * 无序 注册可接受，不可中断、修改
  * 有序 按优先一级级传递，可中断、修改

* 动态广播，代码注册
* 静态广播，`androidMainfest` 注册，，开机，`sd`卡 。

## `ContentProvider`

* 管理对结构化的数据集方问。内部，实现了 增删改查四种操作。 `onCreate` 优先`appliaction`的创建。 `oncreate` 为什么在`application`之前

* 数据的升级

* `sql`语句

  * 创建表

    ~~~
    CREATE TABLE <表名>(<列名> <数据类型>[列级完整性约束条件]
                      [,<列名> <数据类型>[列级完整性约束条件]]…);
    ~~~

  * 删除表`DROP TABLE <表名>;`

  * 清空表 `TRUNCATE TABLE <表名>;`

  * 修改表

    ~~~
    -- 添加列
    ALTER TABLE <表名> [ADD <新列名> <数据类型>[列级完整性约束条件]]
    -- 删除列
    ALTER TABLE <表名> [DROP COLUMN <列名>]
    -- 修改列
    ALTER TABLE <表名> [MODIFY COLUMN <列名> <数据类型> [列级完整性约束条件]]
    ~~~

  * 查询

    ~~~
    SELECT [ALL | DISTINCT] <目标列表达式>[,<目标列表达式>]…
      FROM <表名或视图名>[,<表名或视图名>]…
      [WHERE <条件表达式>]
      [GROUP BY <列名> [HAVING <条件表达式>]]
      [ORDER BY <列名> [ASC|DESC]…]
    ~~~

## 二、自定义View
### 1、paint
* 1、概念
      画笔，保存了绘制几何图形，文本，和位图的样式和颜色
  
* 2、常用api
      常用api主要如颜色，效果和文本相关等。
~~~
        mPaint = new Paint();//初始化
        mPaint.setColor(Color.RED);//设置颜色
        mPaint.setARGB(255, 255, 255, 0);//设置paint对象颜色，范围0~255
        mPaint.setAlpha(200);//设置alpha 不透明，范围0~255
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);//描边效果 FILL 填充;STROKE 描边; FILL_AND_STROKE 填充并表变
        mPaint.setStrokeWidth(4);//描边宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆角效果 BUTT 默认; ROUND 圆角;SQUARE 方形
        mPaint.setStrokeJoin(Paint.Join.MITER);//拐角风格 MITER 尖角;ROUND 切除尖角;BEVEL 圆角
        
        mPaint.setShader(new SweepGradient(200, 200, Color.BLUE, Color.RED));//设置环形渲染器
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));//设置图层混合模式
        mPaint.setColorFilter(new LightingColorFilter(0x00ffff, 0x000000));//设置颜色过滤器
        mPaint.setFilterBitmap(true);//设置双线性过滤
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));//设置画笔遮罩滤镜，传入度数和样式
        mPaint.setTextScaleX(2);//设置文本缩放倍数
        mPaint.setTextSize(38);//设置字体大小
        mPaint.setTextAlign(Paint.Align.LEFT);//对齐方式
        mPaint.setUnderlineText(true);//设置下划线

        String str = "Android 高级工程师";
        final Rect rect = new Rect();
        mPaint.getTextBounds(str, 0, str.length(), rect);//测量文本大小，将文本大小信息存放到rect中
        mPaint.measureText(str);//获取文本的宽
        mPaint.getFontMetrics();//获取字体度量对象
~~~

* 3、Paint详解-颜色相关

      setColor(int color)参数具体的颜色值，16进制数值，0xffff0000
      setARGB(int a,int r,int g,int b) 参数分别透明度，红，绿，蓝。0~255数值
      setShader(Shader shader) 参数着色器对象，一般使用shader的几个子类
            LinearGradient:线性渲染
            RadialGradient:环形渲染
            SweepGradient :扫描渲染
            BitmapShader  :位图渲染
            ComposeShader :组合渲染，例如 LinearGradient+BitmapShader 
      setColorFilter(ColorFilter colorFilter) 设置颜色过滤。一般是ColorFilter三个子类
            LightingColorFilter:光照效果
            PorterDuffColorFIlter:指定已颜色和一种PorterDuff.Mode 与绘制对象进行合成
            ColorMatrixColorFilter:使用一个ColorMatrix来对颜色进行处理
1. linearGradient线性渲染
        
       构造方法：
       LinearGradient(float x0,float x1,float y1,int color0,int color1,Float[]{z1,z2},Shader.TileMode tile)
       参数
       x0 y0 x1 y1:渐变的两个端点的位置
       color0 color1 是端点的颜色
       z1,z2 颜色在布局中开始的比例
       tile：端点范围之外的着色规则，类型是TileMode
       
       TileMode.CLAMP:绘制区域超过渲染区的部分，重复排版
       TileMode.CLAMP:绘制区域超过渲染区的部分，会以最后一个像素拉伸排版
       TileMode.MIRROR:绘制区域超过渲染区的部分,镜像翻转排版
       
       使用：
       mShader = new LinearGradient(0,0,500,500,new int[]{Color.RED,Color.BLUe},
       null,Shader.TileMode.CLAMP);
       mPaint.setShader(mShader);
       canvas.drawCircle(250,250,250,mPaint);

2. RadialGradient 环形渲染

        构造方法:
        RadialGradient(float centerX,float cententY,float radius,int centerColor,int edgeColr,ileMode tileMode)
        
        参数:
        centerX centerY:辐射中心的坐标
        radius:辐射半径
        centerColor:辐射中心的颜色
        edgeColor:辐射边缘的颜色
        tileMode:辐射范围之外的着色规则，类型是TIleMode
        
        使用:
        mShader = new RadialGradient(250, 250, 250, new int[]{Color.GREEN, Color.YELLOW, Color.RED},
                null, Shader.TileMode.CLAMP)
        mPaint.setShader(mShader);
        canvas.drawCircle(250,250,250,mPaint);
    
3. SweepGradient扫描渲染
   
         构造方法:
         SweepGradient(float cx,float cy,int color0,int color1)
         
         参数:
         cx cy :扫描中心
         color0:扫描的其实颜色
         color1:扫描的终止颜色
         
         使用:
         mShader  = new SweepGradient(250,250,Color.RED,Color.GREEN);
         mPaint.setShader(mShader);
         canvas.drawCircle(250,250,250,mPaint);
     
4. 位图渲染

         构造方法
         BitmapShader(Bitmap bitmap,Shader.TileMode titleX,Shader.TileMode tileY)
         
         参数
         bitmap:用来做模板的bitmap对象
         tileX:横向着色规则，类型是TileMode
         tileY:纵向着色规则，类型是TileMode
         
         使用：
         mShader = new BitmapShader(mBitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
         mPaint.setShader(mShader);
         canvas.drawCircle(250,250,250,mPaint);
5. 组合渲染

         构造方法：
         ComposeShader(Shader shaderA,Shader shaderB,PorterDuff.Mode mode)
         
         参数
         shaderA,shaderB:两个相继使用的Shader
         mode:两个Shader的叠加模式，即ShaderA和ShaderB 应该怎样共同绘制。它的类型是PorterDuff.Mode
         
         使用：
         BitmapShader bitmapShader = new BitmapShader(mBItmap,
         Shader.Tile.REPEAT,Shader.TileMode.REPEAT);
         LinearGradient linearGradient = new LInearGradient(0,0,1000,16000,new 
         int[]{Color.RED,Color.GREEN,Color.BLUE},null,Shader.TileMode.CLAMP);
         mShader = new ComposeShader(bitmapShader,linearGradient,PorterDuff.Mode.MULTIPLY);
          mPaint.setShader(mShader);
         canvas.drawCircle(250,250,250,mPaint);
     
7. PorterDuff.Mode图层混合模式

        它将所绘制图形的像素与Canvas中对应位置的像素按照一定规则进行混合，形成新的像素值，从而更新Canvas中
        最终的像素颜色值。
        
        18种模式
        Mode.CLEAR    Mode.SRC      Mode.DST
        Mode.SRC_OVER Mode.DST_OVER Mode.SRC_IN
        Mode.DST_IN   Mode.SRC_OUT  Mode.DST_OUT
        Mode.SRC_ATOP Mode.DST_ATOP Mode.XOR
        Mode.DARKEN   Mode.LIGHTEN  Mode.MULTIPLY
        Mode.SCREEN   Mode.OVERLAY  Mode.ADD
    
    
    ​    
~~~
            //所有绘制不会提交到画布上
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            //显示上层绘制的图像
            new PorterDuffXfermode(PorterDuff.Mode.SRC),
            //显示下层绘制图像
            new PorterDuffXfermode(PorterDuff.Mode.DST),
            //正常绘制显示，上下层绘制叠盖
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            //上下层都显示，下层居上显示
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            //取两层绘制交集，显示上层
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
            //取两层绘制交集，显示上层
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),
            //取上层绘制非交集部分，交集部分变透明
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
            //取下层绘制非交集部分，交集部分变透明
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
            //取上层交集部分与下层非交集部分
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            //取下层交集部分与下层非交集部分
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            //去除两图层交集部分
            new PorterDuffXfermode(PorterDuff.Mode.XOR),
            //取两图层全部区域，交集本分颜色加深
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            //取两图层全部区域，交集本分颜色点亮
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            //取两图层交集部分，颜色叠加
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            //取两图层全部区域，交集部分虑色
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN),
            //取两图层全部区域，交集部分饱和度相加
            new PorterDuffXfermode(PorterDuff.Mode.ADD),
            //取两图层全部区域，交集部分叠加
            new PorterDuffXfermode(PorterDuff.Mode.OVERLAY)
~~~
  ![18种图层混合模式](https://github.com/zh405557524/AndroidAdvanceLearn/blob/master/1_ui/0_ui_core/2_paint/1_paint/device-2019-04-16-212639.png)
8. 离屏绘制

        通过使用离屏缓冲，把要绘制的内容单独绘制在缓冲层，保证Xfermode的使用不会出现错误的结果。
        
        使用离屏绘缓冲有两种方式:
        Canvas.saveLayer() 可以做短时的离屏缓冲，在绘制之前保存，绘制之后恢复:
        int saveId = canvas.saveLayer(0,0,width,height,Canvas.ALL_SAVE_FLAG);
        Canvas.drawBItmap(rectBitmap,0,0,paint);画方
        Paint.setXfermode(xfermode);//设置xfermode
        Canvas.drawBitmap(circleBitmap,0,0,paint);//画圆
        Paint.setXfermode(null);//用完及时清除Xfermode
        cavans.restoreToCount(saveId);
    
    
    ​    
    ​    View.setLayerType() 直接把整个View都绘制在离屏缓冲中。
    ​    setLayerType(LAYER_TYPE_HARDWARE)使用GPU来缓冲，
    ​    setLayerTYpe(LAYER_TYPE_SOFTWARE) 使用一个bitmap来缓冲

* 4、 paint详解-效果相关

1. LightingColorFilter滤镜

       LightingColorFilter
       构造方法：
       LightingColorFilter(int mul,int add)
        
       参数:
       mul 和 add 都是和颜色值格式相同的int值，其中mul用来和目标像素相乘，add用来和目标像素相加:
       R` = R*mul.R/0xff+add.R
       G` = G*mul.G/0xff+add.G
       B` = B*mul.B/0xff+add.B
       
       使用:
       ColorFilter lighting = new LightingColorFilter(0x00ffff,0x000000);
       paint.setColorFilter(lighting);
       canvas.drawBitmap(bitmap,0,0,paint);

2. PorterDuffColorFilter 滤镜

       PorterDuffColorFilter
       构造方法:
       PorterDuffColorFilter(int color,PorterDuff.Mode mode)
       
       参数:
       color,具体的颜色值，例如Color.RED
       mode ,指定PorterDuff.Mode 混合模式
        
       使用:
       PorterDuffColorFilter porterDuffColorFilter = new 
       PorterDuffColorFilter(Color.RED,PorterDuff.Mode.DARKEN);
       paint.setColorFilter(porterDuffColorFilter);
       cavans.drawBitmap(mBitmap,100,0,paint);

3. ColorMatrixColorFilter 滤镜

       ColorMatrixColorFilter
       构造方法:
       ColorMatrixColorFilter(float[] colorMatrix);
       
       参数:
       colorMatrix 矩阵数组 
       
       使用:
       float[] colorMatrix = {
            1,0,0,0,0,//red
            0,1,0,0,0,//green
            0,0,1,0,0,//blue
            0,0,0,1,0 //alpha
       }
       mColorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
       mPaint.setColorFilter(mColorMatrixColorFilter);
       canvas.drawBitmap(mBitmap,100,0,mPaint);
    ![色彩矩阵分析](https://raw.githubusercontent.com/zh405557524/AndroidAdvanceLearn/master/1_ui/0_ui_core/2_paint/1_paint/1671555555375.jpg)  
   
    
### 2、canvas

* 1、概念
  
     画布，通过画笔绘制几何图形、文本、路径和位图等
     
* 2、常用api类型

     常用api分为绘制，变换，状态保存和恢复

1. 绘制几何图形，文本，位图等 

       view drawBitmap(Bitmap bitmap,float left,float top,Paint paint);在指定坐标绘制位图
         
       void drawLine(float startX,float startY,float stopX,float stopY,Paint paint);根据给定的起始点和结束点之间绘制连线
       
       void drawPath(Path path,Paint paint);根据给定的path，绘制连线。
       
       void drawPoint(float X,float y,Paint paint);根据给定的坐标，绘制点。
       
       void drawText(String text,int start,int end,Paint paint);根据给定的坐标，绘制文字
       ...
2. 位置，形状变换等

       void translate(float dx,float dy);平移操作
       
       void scale(float sx,float sy);缩放操作
       
       void rotate(float degrees);旋转操作
       
       void skew(float sx,float sy);倾斜操作
       
       void clipXXX(...);//切割操作，参数指定区域内可以继续绘制
       
       void clipOutXXX(...);反向切割操作，参数指定区域内部不可以绘制
       
       void setMatrix(Matrix matrix);可通过matrix实现平移，缩放，旋转等操作。
   
3. 状态保存和恢复

       Canvas 调用了translate,scale,rotate,skew,clipRect等变化后，后续的操作都是基于变化后的Canvas,
       都会收到影响，对于后续的操作很不方便。Canvas提供了save，saveLayer,saveLayerAlpha,restore,restoreToCount来保存和恢复状态。
       
       int state = canvas.save();//保存状态1 
       canvas.translate(70,50);
       canvas.drawRect(0,0,400,400,mPaint);
       canvas.save();//保存状态2 
       canvas.restore();//返回最新状态(状态2)
       mPaint.setColor(Color.BLUE);
       canvas.drawRect(0,0,400,400,mPaint);
       canvas.restoreToCount(state);//手动指定的返回到状态1
### 3、path
* 1、概念
    路径，可用于绘制直线，曲线构成几何路径，还可用于根据路径绘制文字
* 2、常用api
    常用api如移动，连线，闭合，添加图形等。
    ~~~java
              
            //        //一阶贝塞尔曲线，表示的是一条直线
            //        mPath.moveTo(100, 70); //移动
            ////        mPath.lineTo(140, 800);//连线
            //        //等同于上一行代码效果
            //        mPath.rLineTo(40,730);
            //        mPath.lineTo(250, 600);
            ////        mPath.close();//设置曲线是否闭合
    
            //        //添加子图形addXXX
            //添加弧形
            //        mPath.addArc(200, 200, 400, 400, -225, 225);
    
            //        //Path.Direction.CW表示顺时针方向绘制，CCW表示逆时针方向
            //        mPath.addRect(500, 500, 900, 900, Path.Direction.CW);
            //        //添加一个圆
            //        mPath.addCircle(700, 700, 200, Path.Direction.CW);
            //        //添加一个椭圆
            //        mPath.addOval(0,0,500,300, Path.Direction.CCW);
            //
            //
            //              //追加图形
            //        //xxxTo画线
            //        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
            //
            //        //forceMoveTo，true，绘制时移动起点，false，绘制时连接最后一个点与圆弧起点
            //        mPath.moveTo(0, 0);
            //        mPath.lineTo(100, 100);
            //        mPath.arcTo(400, 200, 600, 400, 0, 270, false);
    
            //              //添加一个路径
            //        mPath.moveTo(100, 70);
            //        mPath.lineTo(140, 180);
            //        mPath.lineTo(250, 330);
            //        mPath.lineTo(400, 630);
            //        mPath.lineTo(100, 830);
            //
            //        Path newPath = new Path();
            //        newPath.moveTo(100, 1000);
            //        newPath.lineTo(600, 1300);
            //        newPath.lineTo(400, 1700);
            //        mPath.addPath(newPath);
    
            //        //添加圆角矩形， CW顺时针，CCW逆时针
            //        RectF rectF5 = new RectF(200, 800, 700, 1200);
            //        // rx ，ry 圆角半径
            //        mPath.addRoundRect(rectF5, 20, 20, Path.Direction.CCW);
            //
            //        //画二阶贝塞尔曲线
            //        mPath.moveTo(300, 500);
            //        //        mPath.quadTo(500, 100, 800, 500);
            //        //参数表示相对位置，等同于上面一行代码
            //        mPath.rQuadTo(200, -400, 500, 0);
            //
    ~~~
    
* 3、[贝塞尔曲线](https://github.com/zh405557524/AndroidNote2/blob/master/CustomView/Advance/%5B06%5DPath_Bezier.md)

* PathMeasure详解
    *  1、概念
        路径测量，一个用来测量path的工具类
    
    * 2、常用api
        常用api如path长度测量，path跳转，path片段获取等。
        [详解](https://github.com/zh405557524/AndroidNote2/blob/master/CustomView/Advance/%5B08%5DPath_Play.md)
        
### 4、matrix
[Camera](https://github.com/GcsSloop/AndroidNote/blob/master/CustomView/Advance/%5B11%5DMatrix_3D_Camera.md)

### 5、anim(动画)
* 1、两种动画
  
    * 1、视图动画
        * Tween Animation(补间动画)
        > 平移动画	translate	TranslateAnimation	移动View
          缩放动画	scale	ScaleAnimation	放大或缩小View
          旋转动画	rotate	RotateAnimation	旋转View
          透明度动画	alpha	AlphaAnimation	改变View的透明度
        
         	 动态的Xml文件，路径为res/anim/filename.xml
      
      ~~~xml
      <?xml version="1.0" encoding="utf-8"?>
        <!--res/anim/filename.xml   -->
      <set xmlns:android="http://schemas.android.com/apk/res/android"
          android:shareInterpolator="true"
          android:duration="300"
          android:fillAfter="true"
          android:interpolator="@android:anim/accelerate_interpolator"
          android:repeatMode="reverse"
          >
          <!--属性
          repeatMode 插值器
           shareInterpolator:表示集合中的动画是否和集合共享同一个插值器。如果集合不指定插值器，那么子动画就需要单独制定所需的插值器或者使用默认值
           duration:动画持续时间
           fillAfater:动画结束以后View是否停留在结束位置，true表示View停留在结束位置，false则不停留
            android:repeatMode  动画重复的Mode
           -->
      
          <alpha
              android:fromAlpha="0.1"
              android:toAlpha="1"></alpha>
          <!--透明度动画
           fromAlpha表示透明度起始值,
           toAlpha表示透明度结束值-->
      
      
          <scale
              android:fromXScale="0.5"
              android:fromYScale="0.5"
              android:pivotX="20"
              android:pivotY="20"
              android:toXScale="1.2"
              android:toYScale="1"
              ></scale>
          <!--缩放动画
          android:fromXScale-水平方向缩放的起始值
          android:toXScale-水平方向缩放结束值
          android:fromYScale-竖直方向缩放的起始值
          android:toScale-竖直方向缩放结束值
          android:pivotX 缩放轴点的x坐标
          android:pivotY缩放轴点的y坐标-->
      
          <translate
              android:fromXDelta="0"
              android:fromYDelta="0"
              android:toXDelta="100"
              android:toYDelta="100"></translate>
          <!-- 平移动画
          android:fromXDelta-表示x的起始值
          android:toXDelta-表示x的结束值
          android:fromYDelta-表示y的起始值
          android:toYDelta-表示y的结束值
          -->
      
          <rotate
              android:fromDegrees="0"
              android:pivotX="10"
              android:pivotY="10"
              android:toDegrees="180"></rotate>
          <!--旋转动画
          android:fromXDelta-旋转开始的角度
          android:toDegrees="180"-旋转结束的角度
          pivotX 缩放轴点的x坐标
          pivotY缩放轴点的y坐标
           默认情况下轴点是View的中心点-->  
      </set>
      
      ~~~
       ~~~android
          mImg = (ImageView) findViewById(R.id.boy);
          Animation animation = AnimationUtils.loadAnimation(ViewAnimationActivity.this, R.anim.view_animation1);
          mImg.startAnimation(animation);
       ~~~
      
       ~~~android
         AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
         alphaAnimation.setDuration(300);
         mImg.startAnimation(alphaAnimation);
         //创建一个透明度动画，将img透明度在300ms内由0变成1
         
         TranslateAnimation translateAnimation = new TranslateAnimation(0,100,0,100);
         mImg.startAnimation(translateAnimation);
         //...
       ~~~
       * FrameAnimation (帧动画)
       ~~~xml
      <?xml version="1.0" encoding="utf-8"?>
          <!-- res/drawable/frame_animation.xml -->
          <animation-list xmlns:android="http://schemas.android.com/apk/res/android"
              android:oneshot="false">
          
              <item
                  android:drawable="@drawable/bga_refresh_loading01"
                  android:duration="100" />
              <item
                  android:drawable="@drawable/bga_refresh_loading02"
                  android:duration="100" />
              <item
                  android:drawable="@drawable/bga_refresh_loading03"
                  android:duration="100" />
          
          </animation-list>
       ~~~
       ~~~android
         mLoading = (ImageView) findViewById(R.id.frameImg);
            mLoading.setBackgroundResource(R.drawable.refresh_loading);
            AnimationDrawable drawable = (AnimationDrawable) mLoading.getBackground();
            drawable.start();
       ~~~
      
      * View Animation的特殊使用场景
        通过LayoutAnimation实现ViewGroup子元素出场动画效果
        1.定义LayoutAnimation
                `// res/anim/anim_layout`
        ~~~xml
        <?xml version="1.0" encoding="utf-8"?>
               <layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
                   android:delay="0.5"
                   android:animation="@anim/anim_item"
                   android:animationOrder="normal">
               </layoutAnimation>
               
               <!-- 
               
                **android:delay**
                 表示子元素开始动画的时间延迟，比如子元素入场动画的时间周期为300ms,那么0.5表示每个元素都要延
                                   迟150ms才能播放入场动画。比如第一个元素延迟150ms，第二个延迟300ms,以此类推。
                 **android:animationOrder**
                表示子元素动画的顺序，有三种选项：normal、reverse和random，分别代表子元素出场按顺序显示、逆向显示和随机显示。          
               **android:animation**
               -->
              
        ~~~
        2.为元素指定具体的入场动画
                `// res/anim/anim_item.xml`
        ~~~xml
        <?xml version="1.0" encoding="utf-8"?>
            <set xmlns:android="http://schemas.android.com/apk/res/android"
                android:duration="300"
                android:interpolator="@android:anim/decelerate_interpolator"
                android:shareInterpolator="true">
            
                <alpha
                    android:fromAlpha="0.0"
                    android:toAlpha="1.0" />
                <scale
                    android:fromYScale="0.5"
                    android:toYScale="1.2" />
            
            </set>
        ~~~
        3.为ViewGroup指定android:layoutAnimation属性。
        ~~~xml
            <ListView
                    android:id="@+id/lv"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layoutAnimation="@anim/anim_layout" />
        ~~~
        * 除了在XML中指定LayoutAnimation外，还可以通过LayoutAnimationController来实现，具体代码如下所示
        ```
            mLv = (ListView) findViewById(R.id.lv);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_item);
            LayoutAnimationController controller = new LayoutAnimationController(animation);
            controller.setDelay(0.5f);
            controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
            mLv.setLayoutAnimation(controller );
        ```
      
    * 2、属性动画
        * 核心类
        |   Java类           | 说明            |
        |    ----            | ----            |
        |  ValueAnimator     | 动画执行类；核心 |
        |  ObjectAnimator    | 动画执行类       |
        |  TimeInterpolator  | 时间插值（插值器接口），控制动画变化率       |
        |  TypeEvaluator     | 类型估值（估值器接口），设置属性值计算方式，根据属性的 始 & 末值 和 插值 一起计算出当前时间的属性值      |
        |  AnimatorSet       | 动画集     |
        |  AnimatorInflater       | 加载属性动画的XML文件     |
        
        * 额外的类
        |   Java类                  | 说明            |
        |    ----                   | ----            |
        |  LayoutTransition         | 布局动画，为布局的容器设置动画 |
        |  ViewPropertyAnimator     | 为View的动画操作提供一种更加便捷的用法 |
        |  PropertyValuesHolder     | 保存动画过程中所需要操作的属性和对应的值 |
        |  Keyframe                 | 控制每个时间段执行的动画距离 |
        |  AnimationListener        | AnimationUpdateListener |
        |  AnimatorListenerAdapter  | 动画事件的监听 |
        
        * 具体使用
        1.`ofInt`
          ~~~android
          //设置动画 始 & 末值
          //ofInt()两个作用：
          //1. 获取实例
          //2. 在传入参数之间平滑过渡
          //如下则0平滑过渡到3
          ValueAnimator animator = ValueAnimator.ofInt(0,3);
          //如下传入多个参数，效果则为0->5,5->3,3->10
          //ValueAnimator animator = ValueAnimator.ofInt(0,5,3,10);
          
          //设置动画的基础属性
          animator.setDuration(5000);//播放时长
          animator.setStartDelay(300);//延迟播放
          animator.setRepeatCount(0);//重放次数
          animator.setRepeatMode(ValueAnimator.RESTART);
          //重放模式
          //ValueAnimator.START：正序
          //ValueAnimator.REVERSE：倒序
          
          //设置更新监听
          //值 改变一次，该方法就执行一次
          animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
              @Override
              public void onAnimationUpdate(ValueAnimator animation) {
                  //获取改变后的值
                  int currentValue = (int) animation.getAnimatedValue();
                  //输出改变后的值
                  Log.d("1111", "onAnimationUpdate: " + currentValue);
                  
                  //改变后的值发赋值给对象的属性值
                  view.setproperty(currentValue);
                  
                  //刷新视图
                  view.requestLayout();
              }
          });
          //启动动画
          animator.start();
          ~~~
          ~~~xml
                // ValueAnimator采用<animator>  标签
                <animator xmlns:android="http://schemas.android.com/apk/res/android"
                    android:duration="1000"
                    android:valueFrom="1"
                    android:valueTo="0"
                    android:valueType="floatType"
                    android:repeatCount="1"
                    android:repeatMode="reverse"/>
                />
          ~~~
          ~~~android
            Animator animator = AnimatorInflater.loadAnimator(context, R.animator.set_animation);  
            // 载入XML动画
            
            animator.setTarget(view);  
            // 设置动画对象
            
            animator.start();  
            // 启动动画
          ~~~
          
        
         2.`ofFloat`与 `ofInt` 一样，返回值不一样
        
         3.`ofObject()`
        
          ~~~android
          
            // 创建初始动画的对象  & 结束动画的对象
            Point point1 = new Point ();  
            Point point2 = new Point ();  
            // 创建动画对象 & 设置参数
            ValueAnimator anim = ValueAnimator.ofObject(new myObjectEvaluator(), point1 , point2 );  
            // 参数说明
            // 1. 自定义的估值器对象（TypeEvaluator 类型参数） - 下面会详细介绍
            // 2. 初始动画的对象
            // 3. 结束动画的对象
            anim.setDuration(length);  
            anim.start();
          ~~~
        
          4.ObjectAnimator
        |   属性             | 作用             |   数值类型      |
        |    ----            | ----            | ----            |
        |  alpha             | 透明度          |        float    |
        |  translationX      | X方向的位移     |        float    |
        |  translationY      | Y方向的位移      |        float    |
        |  scaleX            | X方向的缩放倍数  |        float    |
        |  scaleY           | Y方向的缩放倍数   |        float    |
        |  rotation         | 以屏幕方向为轴的旋转度数 |  float    |
        |  rotationX         | 以X轴为轴的旋转度数 |  float    |
        |  rotationY         | 以Y轴为轴的旋转度数 |  float    |
        
        
           ~~~android
            ObjectAnimator animator = ObjectAnimator.ofFloat(Object object, String property, float ....values);  
            //ObjectAnimator animator = ObjectAnimator.ofInt(Object object, String property, int ....values);  
            //ObjectAnimator animator = ObjectAnimator.ofObject(Object object, String property, TypeEvaluator evaluator,Object....values);  
            
            // 以ofFloat为例 参数说明：
            // Object object：需要操作的对象
            // String property：需要操作的对象的属性
            // float ....values：动画初始值 & 结束值（不固定长度）
            // 若是两个参数a,b，则动画效果则是从属性的a值到b值
            // 若是三个参数a,b,c，则则动画效果则是从属性的a值到b值再到c值
            // 以此类推
            // 至于如何从初始值 过渡到 结束值，同样是由估值器决定，此处ObjectAnimator.ofFloat（）是有系统内置的浮点型估值器FloatEvaluator，同ValueAnimator讲解
            
            //动画基本属性
            anim.setDuration(500); 
            anim.setStartDelay(500);
            anim.setRepeatCount(0);
            anim.setRepeatMode(ValueAnimator.RESTART);
            
            animator.start();  
           ~~~
        
           ~~~xml
               <!--  ObjectAnimator 采用<objectAnimator >  标签 -->
                <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"  
                    android:valueFrom="1"   // 初始值
                    android:valueTo="0"  // 结束值
                    android:valueType="floatType"  // 变化值类型 ：floatType & intType
                    android:propertyName="alpha" // 对象变化的属性名称
                />
           ~~~
        
           ~~~xml
            Animator animator = AnimatorInflater.loadAnimator(context, R.animator.view_animation);  
            // 载入XML动画
            animator.setTarget(view);  
            // 设置动画对象
            animator.start();  
            // 启动动画
           ~~~
           5.AnimatorSet 组合动画
           * 使用
        
            ~~~android
                AnimatorSet.play(Animator anim)   ：播放当前动画
                AnimatorSet.after(long delay)   ：将现有动画延迟x毫秒后执行
                AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
                AnimatorSet.after(Animator anim)   ：将现有动画插入到传入的动画之后执行
                AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行
            ~~~
           * 实例
        
            ~~~android 
                ObjectAnimator a1 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f);  
                ObjectAnimator a2 = ObjectAnimator.ofFloat(view, "translationY", 0f, viewWidth);  
                ......
                AnimatorSet animSet = new AnimatorSet();  
                animSet.setDuration(5000);  
                animSet.setInterpolator(new LinearInterpolator());   
                //animSet.playTogether(a1, a2, ...); //两个动画同时执行  
                animSet.play(a1).after(a2); //先后执行
                ......//其他组合方式
                animSet.start();  
            ~~~
           * xml方式
        
            ~~~xml
                <set
                  android:ordering=["together" | "sequentially"]>
                
                    <objectAnimator
                        android:propertyName="string"
                        android:duration="int"
                        android:valueFrom="float | int | color"
                        android:valueTo="float | int | color"
                        android:startOffset="int"
                        android:repeatCount="int"
                        android:repeatMode=["repeat" | "reverse"]
                        android:valueType=["intType" | "floatType"]/>
                
                    <animator
                        android:duration="int"
                        android:valueFrom="float | int | color"
                        android:valueTo="float | int | color"
                        android:startOffset="int"
                        android:repeatCount="int"
                        android:repeatMode=["repeat" | "reverse"]
                        android:valueType=["intType" | "floatType"]/>
                
                    <set>
                        ...
                    </set>
                </set>
            ~~~
           * 监听动画
        
            ~~~android
                anim.addListener(new AnimatorListener() {
                          @Override
                          public void onAnimationStart(Animation animation) {
                              //动画开始时执行
                          }
                      
                           @Override
                          public void onAnimationRepeat(Animation animation) {
                              //动画重复时执行
                          }
                
                         @Override
                          public void onAnimationCancel()(Animation animation) {
                              //动画取消时执行
                          }
                    
                          @Override
                          public void onAnimationEnd(Animation animation) {
                              //动画结束时执行
                          }
                      });
            ~~~
        
           
        
        
           6.ViewPropertyAnimator
           ~~~android
                    // 使用解析
                    View.animate().xxx().xxx();
                    // ViewPropertyAnimator的功能建立在animate()上
                    // 调用animate()方法返回值是一个ViewPropertyAnimator对象,之后的调用的所有方法都是通过该实例完成
                    // 调用该实例的各种方法来实现动画效果
                    // ViewPropertyAnimator所有接口方法都使用连缀语法来设计，每个方法的返回值都是它自身的实例
                    // 因此调用完一个方法后可直接连缀调用另一方法,即可通过一行代码就完成所有动画效果
                    
                    // 以下是例子
                    mButton = (Button) findViewById(R.id.Button);
                    // 创建动画作用对象：此处以Button为例
            
                    mButton.animate().alpha(0f);
                    // 单个动画设置:将按钮变成透明状态 
                    mButton.animate().alpha(0f).setDuration(5000).setInterpolator(new BounceInterpolator());
                    // 单个动画效果设置 & 参数设置 
                    mButton.animate().alpha(0f).x(500).y(500);
                    // 组合动画:将按钮变成透明状态再移动到(500,500)处
                    
                    // 特别注意:
                    // 动画自动启动,无需调用start()方法.因为新的接口中使用了隐式启动动画的功能，只要我们将动画定义完成后，动画就会自动启动
                    // 该机制对于组合动画也同样有效，只要不断地连缀新的方法，那么动画就不会立刻执行，等到所有在ViewPropertyAnimator上设置的方法都执行完毕后，动画就会自动启动
                    // 如果不想使用这一默认机制，也可以显式地调用start()方法来启动动画
           ~~~
    
* 2、属性动画的原理分析

    
* 3、插值器
            |   Interpolator对象                    | 资源id                                            |   功能作用         |
            |    ----                               | ----                                              | ----              |
            |    AccelerateDecelerateInterpolator   | @android:anim/accelerate_ decelerate_interpolator | 先加速再减速       |
            |    AccelerateInterpolator             | @android:anim/accelerate_ interpolator            | 加速       |
            |    AnticipateInterpolator             | @android:anim/anticipate_ interpolator            | 先回退一小步然后加速前进  |
            |    AnticipateOvershootInterpolator    | @android:anim/anticipate_ overshoot_interpolator  | 在上一个基础上超出终点一小步再回到终点  |
            |    BounceInterpolator                 | @android:anim/bounce_ interpolator                | 最后阶段弹球效果  |
            |    CycleInterpolator                  | @android:anim/cycle_interpolator                  | 周期运动  |
            |    DecelerateInterpolator             | @android:anim/decelerate_ interpolator            | 减速  |
            |    LinearInterpolator                 | @android:anim/linear_interpolator                 | 匀速  |
            |    OvershootInterpolator              | @android:anim/interpolator                        | 快速到达终点并超出一小步最后回到终点  |


* 4、滑动

### 6、touch(事件分发模型)


### 7、screen(屏幕适配)

### 8、project

## 四、Binder

### 一、概述

* 1.1binder时一种进程间通信机制，基于开源的OpenBinder实现。
* 1.2android的4大组件之间的通信依赖Binder IPC机制.系统对应用层的服务：AMS、PMS都是基于Binder IPC机制来实现。
* 1.3linux 已有的ipc ：管道、消息队列、共享内存、socket。binder只需要一次数拷贝，而ssocket/管道/消息队列需要两次。
           binder 基于c/s架构，稳定性好。binder为每个app分配uid，进程的uid是鉴别进程身份的重要标志。（性能、稳定、安全)

### 二、linux下传统的进程通信原理

* 1.1基本概念

  * 进程隔离

    > 1、操作系统中，进程与进程内存是不共享的
    >
    > 2、不同进程数据交互需要采用IPC机制。

  * 进程空间划分:用户空间(User Space)/内核空间(Kernel Space)

    > 1、内核空间是系统内核运行的空间，用户空间是用户程序运行的空间（1G内核/3G用户）
    >
    > 2.它们之间是隔离的。

  * 系统调用:用户态/内核态

    > 1.用户空间对内核空间进行访问，借助系统调用来实现。
    >
    > 2.linux 两级保护机制:0级供系统内核使用  ，3级供用户程序使用。
    >
    > 3.当一个任务(进程)执行系统调用而陷入内核代码中执行时，称进程处于内核运行态(内核态)。此时处理器处于特权级最高的(0级)内核代码中执行。执行的内核代码会使用当前进程的内核栈。每个进程都有自己的内核栈。
    >
    > 4.当进程执行用户自己的代码的时候，我们称其处于用户运行态(用户态)。此时处理器在特权级最低的(3级)用户代码中运行。
    >
    > 5.系统调用主要通过如下两个函数来实现
    >
    > copy_from_user()//将数据从用户空间拷贝到内核空间。
    >
    > copy_to_user() //将数据从内核空间拷贝到用户空间。

* 1.2 linux下的传统ipc通信原理

  > 1.消息发送方：数据 ---放入--->内存缓存区中 ----系统调用----> 内核态 ---分配空间--->内核缓存区 -------copy_from_user-------->用户空间的内存缓存区拷贝到内核空间缓冲区中。
  >
  > 2.消息接受方: 用户空间开辟一块内存缓冲区-----copy_to_user----->将数据从内核缓冲区拷贝到接受进程的内存缓冲区。
  >
  > 缺点：
  >
  > 1.性能低下，一次数据传递需要经历：内存缓冲区--->内核缓冲区----->内存缓冲区，需要2次数据拷贝。
  >
  > 2.接受数据的缓冲区由数据接受进程提供，但是接受进程并不知道需要多大的空间来存放将来传递过来的数据，因此只能开辟尽可能达的内存空间或者先调用API接受消息头获取消息体的大小，这种做法不是浪费空间就是浪费时间。
  >
  > 
  >
  > 通常的做法是消息发送方将要发送的数据存放在内存缓存区中，通过系统调用进入内核态。然后内核程序在内核空间分配内存，开辟一块内核缓存区，调用 copy_from_user() 函数将数据从用户空间的内存缓存区拷贝到内核空间的内核缓存区中。同样的，接收方进程在接收数据时在自己的用户空间开辟一块内存缓存区，然后内核程序调用 copy_to_user() 函数将数据从内核缓存区拷贝到接收进程的内存缓存区。这样数据发送方进程和数据接收方进程就完成了一次数据传输，我们称完成了一次进程间通信。

  

  ![1](/pic_res/binder的由来.jpg)

  ![](/pic_res/binder优点.png)

### 三、binder 跨进程通信原理

* 3.1  动态内核可加载模块 && 内存映射

  * 动态内核可加载模块

    > 1.传统ipc机制 (管道，socket) 都是内核的一部分,binder不是linux系统内核的一部分,
    >
    > 2.linux的 动态内核可加载模块 的机制。模块是具有独立功能的程序，它可以被单独编译，但是不能独立运行。它在运行时被链接到内核做为内核的一部分运行。android系统通过动态添加一个内核模块运行在内核空间。负责各个用户进程通过binder实现通信的内核就叫 Binder 驱动（binder dirver）。

  * 内存映射

    > 1.binder ipc 内存映射通过 mmap()来实现，mmap()是操作系统中一种内存映射的方法。
    >
    > 2.将用户空间的一块内存区域映射到内核空间，
    >
    > 3.映射关系建立后，用户对这块内存区域的修改可以直接反应到内核空间；反之内核空间对这段区域的修改也能直接反应到用户空间。
    >
    > 4.内存映射减少数据拷贝次数。

* 3.2 Binder IPC 实现原理

  * mmap()  内存映射

    > 1.mmap()通常是用在有物理介质的文件系统上。
    >
    > 2.一般读取磁盘上的数据 需要 两次拷贝(磁盘->内核空间->用户空间)；mmap 通过物理介质和用户空间之间建立映射，减少拷贝次数。
    >
    > 3.binder 不存在物理介质，binder使用mmap(),用来在内核空间创建数据接受的缓存空间。

  * 完整的BInder IPC 通信过程通常是这样:

    > 1.首先binder驱动 在内核空间创建了一个**数据接受缓存区**；
    >
    > 2.接着在内核空间开辟一块**内核缓存区**，建立**内核缓存区**和内核中**数据接受缓存区**之间的映射关系，以及内核中数据接受缓存区和**接受进程用户空间地址**的映射关系。
    >
    > 3.发送方进程通过系统调用copy_from_user()将数据copy到**内核缓存区**，由于内核缓存区和接受进程的用户空间存在内存映射，因此也就相当于把数据发送到了接受进程的用户空间，这样便完成了一次进程进程间的通信。

### 四、Binder 通信模型

* 4.1client/server/serviceManger/驱动
  * client、server、serviceManger运行在用户空间，binder驱动在内核空间。serviceManger和binder 由系统提供，client和server由应用程序实现
  * client、server和ServiceManager均是通过系统调用open、mmap和ioctl来访问设备文件/dev/binder，从而实现binder 驱动的交互来间接的实现跨进程通信。
* 4.2 binder 通信过程
  * 1、首先一个进程使用 BINDER_SET_CONTEXT_MGR 命令通过binder驱动将自己注册成为 ServiceManger；
  * 2、server 通过驱动向serverManger 中注册Binder (Server中的bidner 实体)，表明可以对外提供服务。驱动为这个binder创建位于内核中的实体节点以及ServiceManger对实体的引用，将名字以及新建的引用打包传给ServiceManger，ServiceManger将其填入查找表。
  * 3、client通过名字，在binder驱动的帮助下从ServiceMager中获取到对Binder实体的引用，通过这个引用就能实现和Server进程的通信。
* 4.3 Binder 通信中的代理模式
  * A进程获取b进程时的objet 时，驱动会返回一个跟object一样的代理对象，具有跟object一样的方法。 
  * 当binder 驱动接受到A 进程的消息后，发现这是个代理对象(objectProxy)就去查询自己维护的表单，一查发现这个是B进程object的代理对象。于是就会去通知B进程调用object的方法，并要求B进程把返回的结果发给自己。当驱动拿到B进程的返回结果后 就转发给A进程，这样一次通信就完成了。
* 4.4 Binder 的完整定义
  * 从进程间通信的角度看，Binder是一种进程间通信机制；
  * 从server 进程的角度看Binder 指的是Server 中的BInder 实体对象。
  * 从client 进程的角度看，Binder指的是对Binder代理对象，是Binder实体对象的一个远程代理
  * 从传输过程的角度看，Binder 是一个可跨进程传输的对象；Binder驱动会对这个跨越进程边界的对象一点点特殊处理，自动完成代理对象和本地对象之间的转换。
* 线程池
  * 客户端与binder建立链接 是有线程池做管理，当一个进程与binder的连接数大于16时，会被阻塞。

### 五、Binder四个重要对象

* IBinder

  > 只要实现了这个接口 就具备了跨进程的能力

* IInterface

  > server端具备什么样的能力，具备什么样的功能。

* Binder

  > binder 的本地类，代理类

* Stub

  > binder 的本地对象，server端给client的代理类

* 一次binder android 的调用。


## 五、android源码分析

## 六、apk相关，打包，编译，安装，签名
## 七、虚拟机
## 八、性能优化
## 九、热更新



