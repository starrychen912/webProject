import Toast from "../../utils/toast.js";
//获取应用实例
const app = getApp()
const rootUrl = app.globalData.rootUrl;
const rootImgUrl = rootUrl + "/file/fileDown/?saveName=";
Page({
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    Custom: app.globalData.Custom,
    rootUrl: rootUrl,
    rootImgUrl: rootImgUrl,
    cartNum:app.globalData.cartNum,
    chooseNumber:1,//选择商品数量
    goodsId:0,//当前商品ID
    goods:{} //商品信息
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {   
    if(options&&options.id){
      let goodsId=options.id;
      this.setData({
        goodsId:goodsId
      });
      wx.request({
        url: rootUrl + "/wx/goods/wxdetail",
        data: { id: goodsId},
        success: (response) => {
          if (response.data.code == '0000') {
            this.setData({
              goods: response.data.data
            });
          }else{
            Toast.show('fail',null,2000);
          }
        }
      });
    }
  },
  //加入购物车
  addCart(e){
    if (!app.checkLogin()) {
      return false;
    }
    //修改全局购物车数量
    app.globalData.cartNum = app.globalData.cartNum+this.data.chooseNumber;
    this.setData({
      cartNum: app.globalData.cartNum
    })
    //发请求添加购物车
    wx.request({
      url: rootUrl + '/wx/cart/add',
      data: {
        goodsId: this.data.goodsId,
        goodsNum: this.data.chooseNumber,
        price:this.data.goods.price,
        goodsName:this.data.goods.name,
        userId: app.globalData.userData.id,
        userName:app.globalData.userData.name
      },
      success: res => {
        if (res.data.code == '0000') {
          wx.showToast({
            title: '请求成功！',
            duration: 2000,
            mask: true,
            icon: "success",
            success: function () {
              setTimeout(function () {
                wx.switchTab({
                  url: '/pages/cart/cart',
                })
              },2000);
            }
          });
        } else {
          Toast.show('none', res.data.msg, 2000);
        }
      },
      fail: function () {
        Toast.show('fail', res.data.msg, 2000);
      }
    })
  },
  //立即购买
  addOrder(e){
    if (!app.checkLogin()) {
      return false;
    }
    wx.request({
      url: rootUrl + '/wx/order/addImmediately',
      data: {
        goodsId: this.data.goodsId,
        goodsNum: this.data.chooseNumber,
        price: this.data.goods.price,
        goodsName:this.data.goods.name,
        userId: app.globalData.userData.id,
        userName: app.globalData.userData.name
      },
      success: res => {
        if (res.data.code == '0000') {
          wx.showToast({
            title: '请求成功！',
            duration: 2000,
            mask: true,
            icon: "success",
            success: function () {
              setTimeout(function () {
                wx.navigateTo({
                  url: '/pages/order/order',
                })
              }, 2000);
            }
          });
        } else {
          Toast.show('none', res.data.msg, 2000);
        }
      },
      fail: function () {
        Toast.show('fail', res.data.msg, 2000);
      }
    })
  },
  //用户直接输入
  changeNum(e){
    this.setData({
      chooseNumber: e.detail.value ? e.detail.value : "1"
    });
  },
  //数量-1
  handleMin(e){
    let currNum=this.data.chooseNumber;
    if (currNum>1){
      this.setData({
        chooseNumber: currNum-1
      })
    }else{
      Toast.show('fail','不可减少！',1500);
    }
  },
  //数量+1
  handleAdd(e) {
    let currNum = this.data.chooseNumber;
    this.setData({
      chooseNumber: currNum + 1
    })

  },
  //跳到首页
  toPageIndex(e){
    wx.switchTab({
      url: '/pages/index/index',
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
  },
  //跳到首页
  toCart(e) {
    wx.switchTab({
      url: '/pages/cart/cart',
      success: function (res) { },
      fail: function (res) { },
      complete: function (res) { },
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
    app.countCartNum();
    let _that=this;
    wx.getStorage({
      key: 'cartNum',
      success (res) {
        console.log(res.data)
        app.globalData.cartNum=res.data
        _that.setData({
          cartNum: res.data
        })
      }
    })

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