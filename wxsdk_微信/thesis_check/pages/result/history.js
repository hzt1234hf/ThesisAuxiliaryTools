// pages/result/history.js
var app = getApp();
var that = this;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    listData: [
      { "code": "01", "text": "text1", "type": "type1" },
      { "code": "02", "text": "text2", "type": "type2" },
      { "code": "03", "text": "text3", "type": "type3" },
      { "code": "04", "text": "text4", "type": "type4" },
      { "code": "05", "text": "text5", "type": "type5" },
      { "code": "06", "text": "text6", "type": "type6" },
      { "code": "07", "text": "text7", "type": "type7" }
    ],
    id: '',
    showModal: false,
    taskcontent: '',
    maxLength: 240,
    

  },
 

  /**
   * 生命周期函数--监听页面加载
   */


 
  onLoad: function (options) {
    
    var that = this;
    var result = 'xx';
    wx.request({
      url: "http://aux.ziteng.online/user/userlog/get/count/byoid/" + app.globalData.openid,
      method: "GET",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },

      success: function (res) {

        console.log(res.data);
        //console.log(app.globalData.openid);
        result = res.data;
        for (var index in res.data) {
          console.log(res.data[index].logId);
        }

        that.setData({
          listData: result
        })
      },
  
    })
  


    
   

  },


    
  showDialogBtn: function () {
    this.setData({
      showModal: true
    })
  },
  /**
   * 弹出框蒙层截断touchmove事件
   */
  preventTouchMove: function () {
  },
  /**
   * 隐藏模态对话框
   */
  hideModal: function () {
    this.setData({
      showModal: false
    });
  },
  /**
   * 对话框取消按钮点击事件
   */
  onCancel: function () {
    this.hideModal();
  },
  /**
   * 对话框确认按钮点击事件
   */
  onConfirm: function () {
    this.hideModal();
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
    var that = this;
    var result = 'xx';
    wx.request({
      url: "http://118.25.22.27/user/userlog/get/count/byoid/" + app.globalData.openid,
      method: "GET",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },

      success: function (res) {

        console.log(res.data);
        //console.log(app.globalData.openid);
        result = res.data;
        for (var index in res.data) {
          console.log(res.data[index].logId);
        }

        that.setData({
          listData: result
        })
      },

    })

  },


  log_click: function(e){
    console.log("详细报告");
    var that = this
    var result = 'xx';
    var id = e.currentTarget.dataset.id;
    console.log(id)
    that.setData({

      id: id

    })
    wx.request({
      url: "http://aux.ziteng.online/user/userinoutdata/get/bylid/" + id,
      method: "GET",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },

      success: function (res) {

        console.log(res.data);
        if (res.data.inData.length > that.data.maxLength)
          res.data.inData = res.data.inData.substring(0, that.data.maxLength) + '...';
        if (res.data.outData.length > that.data.maxLength)
          res.data.outData = res.data.outData.substring(0, that.data.maxLength) + '...';
        result = res.data;
        that.setData({
          taskcontent: result
        })

        wx.setStorageSync('taskcontent', result);
      },

      

    })

    that.setData({
      showModal: true
    })

    
    /* wx.navigateTo({
       url: '/pages/summary/summary',
    })*/

  },
  logClick: function (e) {
    wx.navigateTo({
      url: '../logs/logs?id=' + e.currentTarget.dataset.id
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
    var that = this;
    var result = 'xx';
    wx.request({
      url: "http://118.25.22.27/user/userlog/get/count/byoid/" + app.globalData.openid,
      method: "GET",
      data: {
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },

      success: function (res) {

        console.log(res.data);
        //console.log(app.globalData.openid);
        result = res.data;
        for (var index in res.data) {
          console.log(res.data[index].logId);
        }

        that.setData({
          listData: result
        })
      },

    })

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