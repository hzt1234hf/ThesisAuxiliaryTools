//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    openid: '',
    personInfo: null,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    imgUrls: [
      'http://aux.ziteng.online/images/wxPhoto1.jpg',
      'http://aux.ziteng.online/images/wxPhoto2.jpg',
      'http://aux.ziteng.online/images/wxPhoto3.jpg'
    ],
    notifications:[],
    autoplay: true,//自动播放
    interval: 2000,//间隔时间
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  itemClick: function (e) {
    wx.navigateTo({
      url: '../article/detail?id=' + e.currentTarget.dataset.id
    })
  },
  onPullDownRefresh: function () {
  },
  onLoad: function () {
    var that = this;
    wx.login({
      success: function (res) {
        if (res.code) {
          //获取openId
          wx.request({
            url: 'https://api.weixin.qq.com/sns/jscode2session',
            data: {
              //小程序唯一标识
              appid:,
              //小程序的 app secret
              secret: ,
              grant_type:,
              js_code: res.code
            },
            // method: 'GET',
            header: { 'content-type': 'application/json' },
            success: function (openIdRes) {
              if (openIdRes.data.openid)
              {
                console.info("登录成功返回的OpenId：" + openIdRes.data.openid);
                that.setData({ openid: openIdRes.data.openid });
                app.globalData.openid = openIdRes.data.openid;


                wx.request({
                  url: "http://aux.ziteng.online/user/userinfo/add",
                  data: {
                    openId: openIdRes.data.openid,
                    wxAccount: openIdRes.data.openid
                    // lastUseDate:null
                  },
                  method: 'POST',
                  header: { 'content-type': 'application/json' },
                  success: function (result) {
                    console.info(result.data);
                  },
                  fail: function (error) {
                  }
                });

                // wx.getUserInfo({
                //   success: function (res) {
                //     console.info(res.userInfo);
                //     app.globalData.personInfo = res.userInfo;
                //     that.setData({ personInfo: res.userInfo });

                //     wx.request({
                //       url: "http://aux.ziteng.online/user/userinfo/add",
                //       data: {
                //         openId: openIdRes.data.openid,
                //         wxAccount: getApp().globalData.personInfo ? getApp().globalData.personInfo.nickName : "test"
                //         // lastUseDate:null
                //       },
                //       method: 'POST',
                //       header: { 'content-type': 'application/json' },
                //       success: function (result) {
                //         console.info(result.data);
                //       },
                //       fail: function (error) {
                //       }
                //     });
                //   }
                // });

              }else{
                app.globalData.openid = "g42353egestyhje54rew";
                console.info("调试模式使用默认OpenId：" + app.globalData.openid);

                wx.request({
                  url: "http://aux.ziteng.online/user/userinfo/add",
                  data: {
                    openId: app.globalData.openid,
                    wxAccount: "test"
                    // lastUseDate:null
                  },
                  method: 'POST',
                  header: { 'content-type': 'application/json' },
                  success: function (result) {
                    console.info(result.data);
                  },
                  fail: function (error) {
                  }
                });
              }

              // 判断openId是否获取成功
            },


            fail: function (error) {
              console.info("获取用户openId失败");
              console.info(error);
            },
          });

          wx.request({
            url:"http://aux.ziteng.online/manage/notification/get/all",
            data:null,
            method: 'GET',
            header: { 'content-type': 'application/json' },
            success: function (result) {
              that.setData({ notifications : result.data.reverse()});
            },
            fail: function (error) {
            }
          });



        }
      }
    });

    // if (app.globalData.userInfo) {
    //   this.setData({
    //     userInfo: app.globalData.userInfo,
    //     hasUserInfo: true
    //   })
    // } else if (this.data.canIUse){
    //   // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //   // 所以此处加入 callback 以防止这种情况
    //   app.userInfoReadyCallback = res => {
    //     this.setData({
    //       userInfo: res.userInfo,
    //       hasUserInfo: true
    //     })
    //   }
    // } else {
    //   // 在没有 open-type=getUserInfo 版本的兼容处理
    //   wx.getUserInfo({
    //     success: res => {
    //       app.globalData.userInfo = res.userInfo
    //       this.setData({
    //         userInfo: res.userInfo,
    //         hasUserInfo: true
    //       })
    //     }
    //   })
    // }
  },




  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },

  jumpPage() {
    console.log(this.data.openid);
    wx.navigateTo({
      //url: '/pages/document/doc_check',
      url: '/pages/document/doc_check',
    })
  },

    jumpPage1() {
      wx.navigateTo({
        url: '/pages/external/out', //
        success: function () {
        }, //成功后的回调；
        fail: function () { }, //失败后的回调；
        complete: function () { } //结束后的回调(成功，失败都会执行)
      })
  }


})
