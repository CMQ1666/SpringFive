/*! ajaxsubmit v1.0.0 | licunzhang 
ajax 提交类
*/
!(function () {
    var __modules__ = {};
    function require(id) {
        var mod = __modules__[id];
        var exports = 'exports';
        if (typeof mod === 'object') {
            return mod;
        }
        if (!mod[exports]) {
            mod[exports] = {};
            mod[exports] = mod.call(mod[exports], require, mod[exports], mod) || mod[exports];
        }
        return mod[exports];
    }

    function define(path, fn) {
        __modules__[path] = fn;
    }
    define("jquery", function () {
        return jQuery;
    });
    // ajaxDialog - 默认配置
    define("ajax-config", {
        id: '',
        // ajax 提交类型 form 表单提交 common 普通提交
        ajaxType: 'form',
        //表单对象 提交类型为form是有效
        form: '',
        //验证码图片名称 提交类型为form时有效
        validateCodeName: '',
        //默认找为submit的提交 按钮 若提供名称 则按照提供的名称 查找提交按钮 提交类型为form是有效
        submitName: '',
        //在提交中提交按钮显示的文本 可为innerhtml
        submitText: "<i class=\"icon-spinner icon-spin  bigger-110 \"></i>正在提交",
        //普通ajax按钮对象
        ajaxBtn: '',
        //是否正在提交
        IsSubmiting: false,
        //跳转属性 0 当前页面跳转 
        //1: 父页面跳转 
        //2: 顶级页面跳转 
        //3: 子页面跳转 
        //4: 叶节点页面跳转 
        //5: 弹出窗体
        //6: 表单提交
        //7: 重置表单
        successRedirect: 0,
        //同上
        failedRedirect: 0,
        //成功后跳转的页面 
        successUrl: '',
        //失败后跳转的页面
        failedUrl: '',
        successAlertOption: {},
        failedAlertOption: {},
        //附加的begin函数
        beginFunction: {},
        //附加的success函数
        successFunction: {},
        //附加的failed函数
        failedFunction: {},
        //附加的complete函数
        completeFunction: {}
    });

    /*!
    * ajaxDialog
    * Date: 2014-11-09
    * https://github.com/aui/ajaxDialog
    * (c) 2009-2014 TangBin, http://www.planeArt.cn
    *
    * This is licensed under the GNU LGPL, version 2.1 or later.
    * For details, see: http://www.gnu.org/licenses/lgpl-2.1.html
    */
    define("lczajax", function (require) {
        var $ = require("jquery");
        var defaults = require("ajax-config");
        var _count = 0;
        var _expando = new Date() - 0; // Date.now()
        var _isIE6 = !('minWidth' in $('html')[0].style);
        var _isMobile = 'createTouch' in document && !('onmousemove' in document) || /(iPhone|iPad|iPod)/i.test(navigator.userAgent);
        var _isFixed = !_isIE6 && !_isMobile;
        var ajaxDialog = function (options, ok, cancel) {
            var originalOptions = options = options || {};
            if (typeof options === 'string' || options.nodeType === 1) {

                options = { content: options, fixed: !_isMobile };
            }
            options = $.extend(true, {}, ajaxDialog.defaults, options);
            options.original = originalOptions;
            var id = options.id = options.id || _expando + _count;
            var api = ajaxDialog.get(id);

            // 如果存在同名的对话框对象，则直接返回
            if (api) {
                return api;
            }
            return ajaxDialog.list[id] = new ajaxDialog.create(options);
        };
        var prototype = ajaxDialog.prototype;
        ajaxDialog.create = function (options) {
            var that = this;
            var originalOptions = options.original;
            this.options = options;
            var submitBtn;
            var resetBtn;
            var validateCode;
            var originalsubmitText = "";

            if (typeof (options.successAlertOption) != 'object') {
                options.successAlertOption = { content: options.successAlertOption };
            }
            if (typeof (options.failedAlertOption) != 'object') {
                options.failedAlertOption = { content: options.failedAlertOption };
            }

            if (options.ajaxType == "form") {
                if (options.submitName == "") {
                    submitBtn = options.form.find(":submit");
                }
                else {
                    submitBtn = options.form.find("name=" + options.submitName);
                }
                resetBtn = options.form.find(":reset");
                validateCode = options.form.find("img[name='" + options.validateCodeName + "']")
            }

            this.Begin = function (xhr, ajaxsetting) {
                //如果是form提交 设置按钮不可用 防止重复提交
                if (options.IsSubmiting)
                    return false;
                options.IsSubmiting = true;
                if (options.ajaxType == "form") {
                    if (submitBtn != undefined) {
                        //获得初始文本
                        originalsubmitText = submitBtn.html();
                        //改变提交按钮的文本
                        submitBtn.html(options.submitText);
                        submitBtn.attr("disabled", true);
                        submitBtn.addClass("disabled")

                    }
                    if (resetBtn != undefined) {
                        resetBtn.attr("disabled", true);
                        resetBtn.addClass("disabled")
                    }
                }
                else {

                    if (typeof (options.ajaxBtn) == "object") {
                        //获得初始文本
                        originalsubmitText = options.ajaxBtn.html();
                        //改变提交按钮的文本
                        options.ajaxBtn.html(options.submitText);
                        options.ajaxBtn.attr("disabled", true);
                        options.ajaxBtn.addClass("disabled")

                    }
                }
                if (typeof (options.beginFunction) === "function") {
                    var newarguments = [];
                    newarguments.push(options);
                    options.beginFunction.apply(this, newarguments);
                }
            };
            this.Success = function (data, status, xhr) {



                switch (data.Result_Type) {
                    case 0: //未登录
                        break;
                    case 1: //执行失败
                        if (data.Result_Message != "" && data.Result_Message != null) {
                            options.failedAlertOption.content = data.Result_Message;
                        }
                        if (data.Redirect_Type != "" && data.Redirect_Type != null) {
                            options.failedRedirect = data.Redirect_Type;
                        }
                        if (data.Redirect_Url != "" && data.Redirect_Url != null) {
                            options.failedUrl = data.Redirect_Url;
                        }
                        if (typeof (options.failedFunction) === "function") {
                            var arguments = [];
                            arguments.push(data);
                            options.failedFunction.apply(this, arguments);
                        }
                        var alertDilaog = alert(options.failedAlertOption, false);
                        if (typeof (options.failedUrl) === "string" && options.failedUrl != "") {
                            if (typeof (alertDilaog.options.callBackFunction) === "function") {
                                var callBackFunction = alertDilaog.options.callBackFunction;
                                alertDilaog.options.callBackFunction = function () {
                                    callBackFunction.apply();
                                    switch (options.failedRedirect) {
                                        case 0: //当前页面跳转
                                            window.location.href = options.failedUrl;
                                        case 1: //父页面跳转
                                            window.parent.location.href = options.failedUrl;
                                            break;
                                        case 2: //顶级页面跳转
                                            window.top.location.href = options.failedUrl;
                                            break;
                                        case 3: //子页面跳转
                                            break;
                                        case 4: //叶节点页面跳转
                                            break;
                                        case 5: //弹出窗体
                                            break;
                                        case 6: //6: 表单提交
                                            break;
                                        case 7: //7: 重置表单
                                            break;
                                        default:
                                            break;
                                    }

                                };
                            }
                            else {
                                alertDilaog.options.callBackFunction = function () {
                                    window.location.href = options.failedUrl;
                                };
                            }
                        }
                        alertDilaog.show();
                        break;
                    case 2: //真正执行成功
                        {
                            if (data.Result_Message != "" && data.Result_Message != null) {
                                options.successAlertOption.content = data.Result_Message;

                            }
                            if (data.Redirect_Type != "" && data.Redirect_Type != null) {
                                options.successRedirect = data.Redirect_Type;

                            }
                            if (data.Redirect_Url != "" && data.Redirect_Url != null) {
                                options.successUrl = data.Redirect_Url;

                            }
                            options.successAlertOption.className = "alert-success";
                            if (options.ajaxType == "form") {
                                //成功后重置表单内容
                                options.form[0].reset();
                            }
                            if (typeof (options.successFunction) === "function") {
                                var arguments = [];
                                arguments.push(data);
                                options.successFunction.apply(null, arguments);
                            }
                            var alertDilaog = alert(options.successAlertOption, false);
                            if (typeof (options.successUrl) === "string" && options.successUrl != "") {
                                if (typeof (alertDilaog.options.callBackFunction) === "function") {
                                    var callBackFunction = alertDilaog.options.callBackFunction;
                                    alertDilaog.options.callBackFunction = function () {
                                        callBackFunction.apply();
                                        switch (options.successRedirect) {
                                            case 0: //当前页面跳转
                                                window.location.href = options.successUrl;
                                            case 1: //父页面跳转
                                                window.parent.location.href = options.successUrl;
                                                break;
                                            case 2: //顶级页面跳转
                                                window.top.location.href = options.successUrl;
                                                break;
                                            case 3: //子页面跳转
                                                break;
                                            case 4: //叶节点页面跳转
                                                break;
                                            case 5: //弹出窗体
                                                break;
                                            case 6: //6: 表单提交
                                                break;
                                            case 7: //7: 重置表单
                                                break;
                                            default:
                                                break;
                                        }

                                    };
                                }
                                else {
                                    alertDilaog.options.callBackFunction = function () {
                                        window.location.href = options.successUrl;
                                    };
                                }
                            }
                            alertDilaog.show();


                        }
                        break;
                    case 3: //系统错误
                        break;
                    case 4: //没有权限
                        break;
                    default:
                        break;

                }

            };
            this.Failed = function (xhr, status, error) {
                var alertDilaog = alert(options.failedAlertOption, false);
                if (typeof (options.failedUrl) === "string" && options.failedUrl != "") {
                    if (typeof (alertDilaog.options.callBackFunction) === "function") {
                        var callBackFunction = alertDilaog.options.callBackFunction;
                        alertDilaog.options.callBackFunction = function () {
                            callBackFunction.apply();
                            switch (options.failedRedirect) {
                                case 0: //当前页面跳转
                                    window.location.href = options.failedUrl;
                                case 1: //父页面跳转
                                    window.parent.location.href = options.failedUrl;
                                    break;
                                case 2: //顶级页面跳转
                                    window.top.location.href = options.failedUrl;
                                    break;
                                case 3: //子页面跳转
                                    break;
                                case 4: //叶节点页面跳转
                                    break;
                                case 5: //弹出窗体
                                    break;
                                case 6: //6: 表单提交
                                    break;
                                case 7: //7: 重置表单
                                    break;
                                default:
                                    break;
                            }

                        };
                    }
                    else {
                        alertDilaog.options.callBackFunction = function () {
                            window.location.href = options.failedUrl;
                        };
                    }
                }
                alertDilaog.show();
            };
            this.Complete = function (xhr, status) {
                options.IsSubmiting = false;
                if (options.ajaxType == "form") {
                    if (submitBtn != undefined) {

                        if (originalsubmitText != "") {
                            submitBtn.html(originalsubmitText);
                        }
                        submitBtn.attr("disabled", false);
                        submitBtn.removeClass("disabled");
                    }
                    if (resetBtn != undefined) {
                        resetBtn.attr("disabled", false);
                        resetBtn.removeClass("disabled");
                    }
                    if (validateCode != undefined) {
                        //需验证图片绑定click事件刷新验证码
                        validateCode.click();
                    }
                }
                else {
                    if (typeof (options.ajaxBtn) == "object") {
                        //获得初始文本
                        if (originalsubmitText != "") {
                            options.ajaxBtn.html(originalsubmitText);
                        }
                        options.ajaxBtn.attr("disabled", false);
                        options.ajaxBtn.removeClass("disabled");

                    }
                }
                if (typeof (options.completeFunction) === "function") {
                    options.completeFunction.apply();
                }
            };
            _count++;
            ajaxDialog.oncreate(this);
            return this;
        };
        ajaxDialog.oncreate = $.noop;
        /** 获取最顶层的对话框API */
        ajaxDialog.getCurrent = function () {
            return Popup.current;
        };
        /**
        * 根据 ID 获取某对话框 API
        * @param    {String}    对话框 ID
        * @return   {Object}    对话框 API (实例)
        */
        ajaxDialog.get = function (id) {
            return id === undefined
    ? ajaxDialog.list
    : ajaxDialog.list[id];
        };
        ajaxDialog.list = {};
        /**
        * 默认配置
        */
        ajaxDialog.defaults = defaults;
        return ajaxDialog;

    });
    window.lczajax = function (options) {
        return require("lczajax")(options);
    }
})();