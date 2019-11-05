// pages/article/detail.js
var app = getApp()
var ROOT_URL = getApp().ROOT_URL
Page({
  data:{
    title:'',
    date:'',
    content:'',
    imgUrl:'',
    modalHidden:true,
    btnHidden:false,
    mid:0
  },
  onLoad:function(options){
    this.data.mid = options.id;
    var that = this;
      //获取焦点图
      wx.request({
        url:'http://aux.ziteng.online/manage/notification/get/bynid/'+parseInt(options.id), 
        method:'GET',
        header: {
            'content-type': 'application/json'
        },
        success: function(res) {
          console.info(res);
          that.setData({
             title:res.data.title,
             content:"\t\t\t" + res.data.content,
             date:res.data.noticeDate
          })
        },
        fail:function(res){
          that.showError();
        }
      });
  },
  showError:function(){
      wx.showModal({
        title: '提示',
        content: '很抱歉，网络繁忙，请稍后重试！',
        showCancel: false
      })
  },
   modalTap: function (e) {
    this.setData({
        modalHidden: false
    })
  },
  modalHide: function(e) {
    this.setData({
        modalHidden: true
    })
  },
  touchHide:function(e){
     this.setData({
        btnHidden: true
    })
  },
  touchShow:function(e){
    this.setData({
        btnHidden: false
    })
  }
})