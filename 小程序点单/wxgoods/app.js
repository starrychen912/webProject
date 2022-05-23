//app.js
import Toast from "/utils/toast.js";
App({
  globalData: {
    userInfo: null,//微信用户信息
    userData:null, //登录后返回的用户信息
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    cartNum:0,//购物车商品数量
    orderNum:{},//当前用户订单数量
    rootUrl: "http://localhost:8085"//如果用手机，务必修改localhost为你本机IP地址，并且手机、电脑在同一个网络
  },
  onLaunch: function() {
    let userDataObj = wx.getStorageSync('userData');
    if (userDataObj && userDataObj.id){
      this.globalData.userData = userDataObj;
    }
    //从storage中获取微信用户信息
    let userInfoObj = wx.getStorageSync('userInfo');
    if (userInfoObj && userInfoObj.nickName){
      //说明用户已经授权
        this.globalData.userInfo=userInfoObj;
    }else{
      // 如果用户已经授权则获取用户信息
      wx.getSetting({
        success: res => {
          if (res.authSetting['scope.userInfo']) {
            // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
            wx.getUserInfo({
              success: res => {
                // 可以将 res 发送给后台解码出 unionId
                this.globalData.userInfo = res.userInfo
                // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
                // 所以此处加入 callback 以防止这种情况
                if (this.userInfoReadyCallback) {
                  this.userInfoReadyCallback(res)
                }
              }
            })
          }
        }
      })
    }
    this.countOrder();
    this.countCartNum();
    // 获取系统状态栏信息
    wx.getSystemInfo({
      success: e => {
        console.log('e:',e)
        this.globalData.StatusBar = e.statusBarHeight;//状态栏的高度
        let capsule = wx.getMenuButtonBoundingClientRect();//获取菜单按钮（右上角胶囊按钮）的布局位置信息
        console.log('capsule:',capsule)
        if (capsule) {
         	this.globalData.Custom = capsule;
        	this.globalData.CustomBar = capsule.bottom + capsule.top - e.statusBarHeight;
        } else {
        	this.globalData.CustomBar = e.statusBarHeight + 50;
        }
      }
    })
  },
  checkLogin:function(e){
    console.log("进入app.checkLogin");
    if(this.globalData.userData){
      return true;
    }else{
      //提示并跳到登录
      Toast.showToLogin(null,null);
    }
  },
  //计算用户订单数量：待配货 待接单 待评价
  countOrder(e){
    let rootUrl = this.globalData.rootUrl;
    if (this.globalData.userData){
      //说明用户已经登录，可以查找
      wx.request({
        url: rootUrl + '/wx/order/countOrderNum',
        data: {
          userId: this.globalData.userData.id
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: (ret) => {
          if (ret.data.code == '0000') {
            this.globalData.orderNum=ret.data.data
          } 
        }
      })
    }
  },
  countCartNum(e){
    let rootUrl = this.globalData.rootUrl;
    if (this.globalData.userData){
      //说明用户已经登录，可以查找
      wx.request({
        url: rootUrl + '/wx/cart/countCartNum',
        data: {
          userId: this.globalData.userData.id
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: (ret) => {
          if (ret.data.code == '0000') {
            wx.setStorageSync('cartNum', ret.data.data)
            this.globalData.cartNum=ret.data.data
            return ret.data.data;
          } 
        }
      })
    }
  }
  
})