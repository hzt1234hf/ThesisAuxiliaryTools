// pages/feedback/feedback.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

    contact: '',
    contant: '',

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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


  bindTextAreaBlur_contact: function (e) {
    this.setData({
      contact: e.detail.value
    })

  },


  bindTextAreaBlur_contant: function (e) {
    this.setData({
      contant: e.detail.value
    })

  },


  formSubmit: function(){
    if (this.data.contact.replace(/\s+/g, '') == '') {
      wx.showToast({
        title: '请先输入,再进行提交!',
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (this.data.contant.replace(/\s+/g, '') != '' && (this.data.contant.replace(/(^1\d{10}$)|(^[0-9]\d{7}$)/, '') == '' || this.data.contant.replace(/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/, '') == ''))
    {
      wx.showToast({
        title: '请正确输入邮箱或密码',
        icon: 'none',
        duration: 2000
      });
      return;
    }
    
    wx.request({
      url: "http://aux.ziteng.online/user/userfeedback/add",
      method: "POST",
      data: {
        openId: app.globalData.openid,
        content: this.data.contact,
        contact: this.data.contant,
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success: function (res) {
        // console.log(res.data);
        wx.showToast({
          title: "提交成功,感谢您的支持",
          icon: 'none',
          duration: 5000
        })
      },
    })



    this.setData({
      contact: '',
      contant: ''
    })


    wx.navigateTo({
      url: '../index/index',
    })

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})