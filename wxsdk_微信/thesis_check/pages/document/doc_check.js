// pages/doc_check.js
var app = getApp();
Page({


  data: {
    show: true,
    empty: '请点击您要查看的文件',
    fileList: [],
    fileListUrl: [],
    typo_main: '',
    theme_main: '',
    url: '',
    path: '',
    first: '',
    second: '',
    selected: 0,
    list: ['使用帮助', '相似度分析', '错别字检测', '主题提取'],


    currentTab: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  swiperTab: function (e) {
    console.log(e);
    this.setData({
      currentTab: e.detail.current
    });
  },
  //点击切换
  clickTab: function (e) {
    var _this = this;
    if (_this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      _this.setData({
        currentTab: e.target.dataset.current
      })
    }
  },
  on_text_submit: function () {
    var that = this
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {
        var tempFilePaths = res.tempFilePaths
        console.log(tempFilePaths)
        that.setData({
          path: tempFilePaths
        })
      }
    })
  },




  bindTextAreaBlur_main: function (e) {
    this.setData({
      typo_main: e.detail.value
    })

  },

  bindTextAreaBlur_theme: function (e) {
    this.setData({
      theme_main: e.detail.value
    })

  },


  bindTextAreaBlur1: function (e) {
    this.setData({
      first: e.detail.value
    })

  },

  bindTextAreaBlur2: function (e) {
    this.setData({
      second: e.detail.value
    })

  },


  on_typo_submit: function (e) {
    if (this.data.typo_main.replace(/\s+/g, '') == '') {
      wx.showToast({
        title: '请先输入短文本!',
        icon: 'none',
        duration: 2000
      });
      return;
    }

    wx.request({
      url: "http://aux.ziteng.online/aux/correction",
      method: "POST",
      data: {
        openid: app.globalData.openid,
        text: this.data.typo_main,
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success: function (res) {
        console.log(res.data);
        //console.log('===========');
        //console.log(app.globalData.openid);
        wx.navigateBack({
          delta: 1  //小程序关闭当前页面返回上一页面
          //url: '../result/history'
        })
        wx.showToast({
          title: res.data,
          icon: 'none',
          duration: 5000
        })
      },
    })
  },


  bcode_click: function () {
    var that = this;
    var content;
    wx.scanCode({
      success: (res) => {
        this.content = res.result;
        that.setData({
          first: this.content
        })
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        })
      },
      complete: (res) => {
      }
    })
  },

  bcode_click_sec: function () {
    var that = this;
    var content;
    wx.scanCode({
      success: (res) => {
        this.content = res.result;
        that.setData({
          second: this.content
        })
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        })
      },

      complete: (res) => {
      }
    })
  },


  bcode_click_typo: function () {
    var that = this;
    var content;
    wx.scanCode({
      success: (res) => {
        this.content = res.result;
        that.setData({
          typo_main: this.content
        })
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        })
      },
      complete: (res) => {
      }
    })
  },

  bcode_click_theme: function () {
    var that = this;
    var content;
    wx.scanCode({
      success: (res) => {
        this.content = res.result;
        that.setData({
          theme_main: this.content
        })
        wx.showToast({
          title: '成功',
          icon: 'success',
          duration: 2000
        })
      },

      complete: (res) => {
      }
    })
  },

  on_theme_submit: function (e) {
    if (this.data.theme_main.replace(/\s+/g, '') == '') {
      wx.showToast({
        title: '请先输入短文本!',
        icon: 'none',
        duration: 2000
      });
      return;
    }

    wx.request({
      url: "http://aux.ziteng.online/aux/theme",
      method: "POST",
      data: {
        openid: app.globalData.openid,
        text: this.data.theme_main,
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success: function (res) {
        console.log(res.data);
        //console.log('===========');
        //console.log(app.globalData.openid);
        wx.navigateBack({
          delta: 1  //小程序关闭当前页面返回上一页面
          //url: '../result/history'
        })
        wx.showToast({
          title: res.data,
          icon: 'none',
          duration: 5000
        })
      },
    })
  },



  on_text_submit: function (e) {
    if (this.data.first.replace(/\s+/g, '') == '' || this.data.second.replace(/\s+/g, '') == '') {
      wx.showToast({
        title: '请先输入短文本!',
        icon: 'none',
        duration: 2000
      });
      return;
    }

    wx.request({
      url: "http://aux.ziteng.online/aux/similarity",
      method: "POST",
      data: {
        openid: app.globalData.openid,
        first: this.data.first,
        second: this.data.second,
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success: function (res) {
        console.log(res.data);
        wx.navigateBack({
          delta: 1  //小程序关闭当前页面返回上一页面
          //url: '../result/history'
        })
        wx.showToast({
          title: res.data,
          icon: 'none',
          duration: 5000
        })
      },
    })
  },


  



  jumpPage: function () {
    var url = this.data.url || '';
    if (!url) {
      wx.showToast({
        title: '请先选择文件!',
        icon: 'success',
        duration: 2000
      });
      setTimeout(function () {
        wx.hideToast();
      }, 1000);
      return;
    }
  },


  radioChange: function () {
    var that = this;
    that.setData({
      show: (!that.data.show)
    })
  },

  selected: function (e) {
    console.log(e)
    let that = this
    let index = e.currentTarget.dataset.index
    console.log(index)
    if (index == 0) {
      that.setData({
        selected: 0
      })
    } else if (index == 1) {
      that.setData({
        selected: 1
      })
    } else if(index == 2) {
      that.setData({
        selected: 2
      })
    } else{
      that.setData({
        selected: 3
      })
    }
  },


  upload: function () {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success(res) {
        // tempFilePath可以作为img标签的src属性显示图片
        const tempFilePaths = res.tempFilePaths[0]
        that.setData({
          userHeaderImage: tempFilePaths
        })
        // console.log(tempFilePaths)

        wx.showToast({
          title: tempFilePaths,
          icon: 'success',
          duration: 20000
        });
        //上传图片
        wx.uploadFile({
          url: 'https://xxxx/api/wxtest/uploadfile', //仅为示例，非真实的接口地址
          filePath: tempFilePaths,
          name: 'file',
          // header: {
          //   "content-tsype": "multipart/form-data",
          //   'content-type': 'application/x-www-form-urlencoded' //表单提交
          // },
          formData: {
            'userId': 10001
          },
          success(res) {
            const data = res.data
            console.log(res);
            //do something
          }
        })
      }
    })
  },







})