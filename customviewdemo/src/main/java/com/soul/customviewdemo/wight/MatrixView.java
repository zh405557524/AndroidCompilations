package com.soul.customviewdemo.wight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.soul.customviewdemo.R;

import java.util.Arrays;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.customviewdemo.wight
 * @作者：祝明
 * @描述：介绍Matrix的原理及其使用
 * @创建时间：2017/6/3 11:52
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 * <p>
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 */

/**
 * Matrix 原理
 * 1 Matrix是一个矩阵，主要功能是坐标映射，数值转换
 * [  MSC ALE_X     MSK EW_X    MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y   MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 2 4种基本变化:平移(translate)、缩放(scale)、旋转(rotate)和错切(skew)。
 * <p>
 * 旋转(rotate)             位移(translate)
 * [ MSC ALE_X MSK EW_X          MTR ANS_X ]
 * MSK EW _Y MSC ALE_Y         MTR ANS_Y
 * <p>
 * MPE RSP_0 MPE RSP_1 MPE RSP_2
 * 透视(perspective)
 * <p>
 * 缩放(scale)     错切(skew)
 * [  MSC ALE_X     MSK EW_X       MTR ANS_X  ]
 * MSK EW_Y      MSC ALE_Y     MTR ANS_y
 * MPE RSP_0     MPE RSP_1   MPE RSP_2
 * <p>
 * 3 缩放(scale)
 * x  = k1x0
 * y = k2y0
 * 用矩阵表示:
 * [x]    [k1 0  0] [x0]
 * [y]  = [0  k2 0] [y0]
 * [1]    [0  0  1] [1]
 * 4 错切(Skew)
 * 水平错切
 * x = x0+ky0
 * y = y0
 * 用矩阵表示
 * [x]  [1 k 0] [x0]
 * [y]= [0 1 0] [y0]
 * [1]  [0 0 1] [1]
 * 垂直错切
 * x= x0
 * y= kx0+y0
 * 用矩阵表示
 * [x]  [1 0 0] [x0]
 * [y]= [k 1 0] [y0]
 * [1]  [0 0 1] [1]
 * <p>
 * 5 旋转(rotate)
 * 假定一个点 A(x0, y0) ,距离原点距离为 r, 与水平轴夹角为 α 度,
 * 绕原点旋转 θ 度, 旋转后为点 B(x, y) 如下:
 * x0 = r*cons α
 * y0 = r*sin α
 * x = r*cons(a+θ) =r*cons α*cos θ-r*sin a*sin θ = x0*cons θ -y0*sin θ
 * y = r*sin(a+θ)  =r*sin a * cons θ +r*cons a *sin θ = y0*cons θ +s0*sin θ
 * <p>
 * 用矩阵表示:
 * [x]  [cons(θ) -sin(θ) 0] [x0]
 * [y]= [sin(θ)  cons(θ) 0] [y0]
 * [1]  [0       0       1] [1]
 * <p>
 * 5 平移(Translate)
 * x= x0+△x
 * y= y0+△y
 * 用矩阵表示
 * [x]  [1 0 △x] [x0]
 * [y]= [0 1 △y] [y0]
 * [1]  [0 0  1] [1]
 * <p>
 * 6 Matrix复合原理
 * 前乘(pre)
 * M'= M*S
 * 后乘(post)
 * M'= S*M
 * 设置(set)
 * 覆盖原先数值
 */

/**
 *Matrix详解
 * 1 数值操作
 *  set reset setValues getValues                   ------>设置、重置、设置数值、获取数值
 * 2 数值计算
 *  mapPoints mapRadius mapRect mapVectors          ------>计算变换后的数值
 * 3 设置
 *  setConcat setRotate setScale setSkew setTranslate----->设置变换
 * 4 前乘
 *  preConcat preRotate preScale preSkew            ------>前乘变换
 * 5 后乘
 *  postConcat postRotate postScale postSkew        ------>后乘变换
 * 6 特殊方法
 *  setPolyTPoly setRectToRect rectStaysRect setSinCos --->一些特殊操作
 * 7 矩阵相关
 *  invert isAffine(API21) isIdentity                 ---->求逆矩阵、 是否为仿射矩阵、 是否为单位矩阵 …
 */

public class MatrixView extends View {


    private Bitmap mBitmap;
    private Matrix mPolyMatrix;
    private int mViewWidth;
    private int mViewHeight;

    public MatrixView(Context context) {
        this(context, null);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinv);
        mPolyMatrix = new Matrix();
        float[] src = {0, 0,                         //左上
                mBitmap.getWidth(), 0,               //右上
                mBitmap.getWidth(), mBitmap.getHeight(),//右下
                0, mBitmap.getHeight()                   //左下
        };
        float[] dst = {0, 0,                         //左上
                mBitmap.getWidth(), 400,             //右上
                mBitmap.getWidth(), mBitmap.getHeight() - 200,//右下
                0, mBitmap.getHeight()                   //左下
        };

        //核心要点
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);

        mPolyMatrix.postScale(0.26f, 0.26f);
        mPolyMatrix.postTranslate(0, 200);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**------------------------------mapPoint--------------------------------*/

        //计算一组点基于当前Matrix变换后的位置
        //        mapPoints();
        //src作为参数传递原始数值，计算结果存放在dst中，src不变。
        //        mapPoints1();
        /**
         * 可以指定只计算一部分数值。
         */
        //        mapPoints2();

        /**------------------------------mapRadius--------------------------------*/
        //测量半径，由于圆可能会因为画布变换变成椭圆，所以此处测量的是平均半径。
        //        rapRadius();

        /**------------------------------mapRectF--------------------------------*/
        //        rapRectF();
        /**------------------------------mapVectors--------------------------------*/
        //mapVectors 与 mapPoints 基本上是相同的，可以直接参照上面的mapPoints使用方法。
        //        mapVectors();

        /**------------------------------setPolyToPoly--------------------------------*/
        //        setPolyToPoly(canvas);
        setRectToRect(canvas);

    }

    private void setRectToRect(Canvas canvas) {
        Matrix matrix = new Matrix();
        RectF src = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        RectF dst = new RectF(0, 0, mViewWidth, mViewHeight);

        //核心
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        canvas.drawBitmap(mBitmap,matrix,new Paint());
    }

    private void setPolyToPoly(Canvas canvas) {
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);

    }

    private void mapVectors() {
        float[] src = {1000, 800};
        float[] dst = new float[2];
        //构造一个matrix
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        matrix.postTranslate(100, 100);
        //计算向量，不受位移影响
        matrix.mapVectors(dst, src);
        Log.i("Tag", "mapVectors:" + Arrays.toString(dst));
        //计算点
        matrix.mapPoints(dst, src);
        Log.i("Tag", "mapPoints:" + Arrays.toString(dst));

    }

    private void rapRectF() {
        RectF rectF = new RectF(400, 400, 1000, 800);
        //构造一个matrix
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        matrix.postSkew(1, 0);

        Log.i("Tag", rectF.toString());
        boolean result = matrix.mapRect(rectF);
        Log.i("Tag", "mapRadius:" + rectF.toString());

        Log.i("Tag", "isRect:" + result);
    }

    private void rapRadius() {
        float radius = 100;
        float result = 0;
        //构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        Log.i("Tag", "mapRadius:" + radius);
        result = matrix.mapRadius(radius);
        Log.i("Tag", "result:" + result);

    }

    /**
     *   参数	摘要
     dst	目标数据
     dstIndex	目标数据存储位置起始下标
     src	源数据
     srcIndex	源数据存储位置起始下标
     pointCount	计算的点个数
     */
    private void mapPoints2() {
        //初始数据为三个点(0,0)(80,100)(400,300)
        float[] src = {0, 0, 80, 100, 400, 300};
        float[] dst = new float[6];
        //构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        //输出计算之前数据
        Log.i("Tag", "before:src=" + Arrays.toString(src));
        Log.i("Tag", "before:dst=" + Arrays.toString(dst));

        //调用map方法计算(最后一个2表示两个点，即四个数值，并非两个数值)
        matrix.mapPoints(dst, 2, src, 2, 2);
        //输出计算之后数据
        Log.i("Tag", "after:src=" + Arrays.toString(src));
        Log.i("Tag", "after:dst=" + Arrays.toString(dst));

    }

    private void mapPoints1() {
        //初始数据为三个点(0,0)(80,100)(400,300)
        float[] src = {0, 0, 800, 100, 400, 300};
        float[] dst = new float[6];
        //构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        //输出计算之前数据
        Log.i("Tag", "before:src=" + Arrays.toString(src));
        Log.i("Tag", "before:dst=" + Arrays.toString(dst));

        //调用map方法计算
        matrix.mapPoints(dst, src);

        //输出计算之后数据
        Log.i("Tag", "after:src=" + Arrays.toString(src));
        Log.i("Tag", "after:dst=" + Arrays.toString(dst));

    }

    private void mapPoints() {
        //初始化数据为三个点
        float[] pts = {0, 0, 80, 100, 400, 300};
        //构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        //输出pts计算之前数据
        Log.i("Tag", "before:" + Arrays.toString(pts));
        //调用map方法计算
        matrix.mapPoints(pts);
        Log.i("Tag", "after:" + Arrays.toString(pts));
    }

}
