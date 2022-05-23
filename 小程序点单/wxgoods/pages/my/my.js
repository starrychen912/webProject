import Toast from "../../utils/toast.js";
let toast=new Toast();
//获取应用实例
const app = getApp()
const rootUrl = app.globalData.rootUrl;
Page({
  /**
   * 页面的初始数据
   */
  data: {
    rootUrl: rootUrl,
    isShowBtn: true,
    userInfo: null,
    hasUserInfo: false,
    canIUseGetUserProfile:true,
    userData: null,
    storageSize: 0,
    orderNum: {},
    storageUseSize: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取缓存
    this.getCache();
    if (app.globalData.userInfo && app.globalData.userInfo.avatarUrl) {
      this.setData({
        userInfo: wx.getStorageSync('userInfo'),
        hasUserInfo: true
      });
    }
    console.log(app.globalData)
    if (app.globalData.userData && app.globalData.userData.id>0) {
      this.setData({
        userData: wx.getStorageSync('userData')
      });
      console.log("要开始调用countOrder")
      this.countOrder();
    }
  },
  //计算订单数量
  countOrder(e) {
    if (app.globalData.userData) {
      //说明用户已经登录，可以查找
      wx.request({
        url: rootUrl + '/wx/order/countOrderNum',
        data: {
          userId: app.globalData.userData.id
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: (ret) => {
          if (ret.data.code == '0000') {
            app.globalData.orderNum = ret.data.data
            this.setData({
              orderNum:ret.data.data
            })
          }
        }
      })
    }
  },
  //跳到我的订单页面
  toMyOrder(e){
    if (!app.checkLogin()) {
      return false;
    }
    wx.navigateTo({
      url: '/pages/order/order',
    })
  },
  getCache: function (e) {
    //获取当前缓存数据大小
    try {
      const res = wx.getStorageInfoSync()
      this.setData({
        storageSize: res.limitSize,
        storageUseSize: res.currentSize
      });
    } catch (e) {
      // Do something when catch error
    }
  },

  
  bindGetUserInfo(e) {
    //用户点击了允许
    if (e.detail.rawData) {
      console.log("用户进行了授权");
      console.log(e.detail.userInfo)
      //授权后刷新页面
      this.getUserInfo();
    }
  },

  getUserProfile(e) {
    let _this=this;
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        wx.setStorageSync('userInfo', res.userInfo);
        app.globalData.userInfo=res.userInfo;
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        }, () => {
          if (_this.data.userInfo) {
            wx.navigateTo({
              url: '/pages/login/login',
            })
          }
        })
      }
    })
  },
  getUserInfo: function (e) {
    let _this = this;
    wx.getSetting({
      success: (data) => {
        if (data.authSetting['scope.userInfo']) {
          console.log("用户已经授权");
          this.setData({
            isShowBtn: false
          });
          wx.getUserInfo({
            success: res => {
              console.log(res);
              app.globalData.userInfo = res.userInfo
              wx.setStorageSync('userInfo', res.userInfo)
              //为当前页面实例设置数据
              this.setData({
                userInfo: res.userInfo,
                hasUserInfo: true
              }, () => {
                if (_this.data.userInfo) {
                  wx.navigateTo({
                    url: '/pages/login/login',
                  })
                }
              })
            },
            fail: () => {
              console.log("获取用户信息失败");
            }
          })
        }
      }
    });
  },
  //跳转到用户详情页
  toUserInfo: function (event) {
    if (!app.checkLogin()) {
      return false;
    }
    let userId = app.globalData.userData.id;
    wx.navigateTo({
      url: '/pages/userDetail/userDetail?id=' + userId,
    })
  },
  //清除缓存
  toClearCache: function (event) {
    try {
      wx.clearStorage({
        success: res => {
          wx.showToast({
            title: '清理成功！',
            icon: 'success',
            success: (e) => {
              //获取缓存
              this.getCache();
              console.log("清理缓存成功！");
              this.onLoad();
            }
          })
        }
      });
    } catch (e) {
      // Do something when catch error
    }
  },
  //跳转到系统说明
  toSystem: function (event) {
    wx.navigateTo({
      url: '/pages/system/system',
    })
  },
  //退出方法
  exit(e) {
    app.globalData.userData = null;
    wx.removeStorageSync('userData')
    // wx.setStorageSync('userData', null)
    wx.navigateTo({
      url: '/pages/login/login',
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log(app.globalData)
    if (app.globalData.userData && app.globalData.userData.id > 0) {
      this.setData({
        userData: app.globalData.userData
      });
      console.log("要开始调用countOrder")
      this.countOrder();
    }


  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
