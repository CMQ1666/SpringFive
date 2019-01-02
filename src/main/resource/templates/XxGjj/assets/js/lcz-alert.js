/*! alertDialog v1.0.0 | licunzhang 
三种形式 弹出
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
    // alertDialog - 默认配置
    define("alert-config", {
        id: '',
        //bootstrap的 alert附加样式 例如 alert-infor alert-danger 
        className: 'alert-danger',
        //弹出框 弹出形式 boot表示 页面显示弹出框内容 old 表示 传统弹出框 art 表示artdialog 弹出框
        alertType: 'boot',
        //弹出框 父容器ID 只有在alertType 为boot时 才应用到该项。
        parentDivId: 'alertContent',
        //弹出框 添加形式 replace 替代 append 追加 只有在alertType 为boot时 才应用到该项。
        addType: 'replace',
        //弹出框 内容
        content: '',
        artOptions: { title: "提示" },
        callBackFunction: {}
    });

    /*!
    * alertDialog
    * Date: 2014-11-09
    * https://github.com/aui/alertDialog
    * (c) 2009-2014 TangBin, http://www.planeArt.cn
    *
    * This is licensed under the GNU LGPL, version 2.1 or later.
    * For details, see: http://www.gnu.org/licenses/lgpl-2.1.html
    */
    define("lczAlert", function (require) {
        var $ = require("jquery");
        var defaults = require("alert-config");
        var _count = 0;
        var _expando = new Date() - 0; // Date.now()
        var _isIE6 = !('minWidth' in $('html')[0].style);
        var _isMobile = 'createTouch' in document && !('onmousemove' in document) || /(iPhone|iPad|iPod)/i.test(navigator.userAgent);
        var _isFixed = !_isIE6 && !_isMobile;
        var alertDialog = function (options, ok, cancel) {
            if (typeof (options) != 'object')
                options = { content: options.toString() };
            var originalOptions = options = options || {};
            if (typeof options === 'string' || options.nodeType === 1) {

                options = { content: options, fixed: !_isMobile };
            }
            options = $.extend(true, {}, alertDialog.defaults, options);
            options.original = originalOptions;
            var id = options.id = options.id || _expando + _count;
            var api = alertDialog.get(id);
            // 如果存在同名的对话框对象，则直接返回
            if (api) {
                return api.focus();
            }
            if (ok == undefined)
                ok = true;
            return alertDialog.list[id] = new alertDialog.create(options, ok);
        };
        var prototype = alertDialog.prototype;
        alertDialog.create = function (options, ok) {
            
            var that = this;
            var originalOptions = options.original;
            this.options = options;
            $.each(options, function (name, value) {
                if (typeof that[name] === 'function') {
                    that[name](value);
                } else {
                    that[name] = value;
                }
            });
            this.show = function () {
                switch (options.alertType) {
                    case 'boot':
                        if ($("#" + options.parentDivId).length == 0) {
                            if (options.content!="")
                           _alert(options.content);
                            if (typeof (options.callBackFunction) === "function")
                                options.callBackFunction.apply();
                            return;
                        }
                        if ($("#" + options.id).length > 0) {
                            $("#" + options.id).remove();
                        }

                        var lczalerthtml = "<div id=\"" + options.id + "\" class=\"alert " + options.className + "\"><i class=\"icon-hand-right\"></i>" + options.content.toString() + "<button class=\"close\" data-dismiss=\"alert\"><i class=\"icon-remove\"></i></button></div>";
                        if (options.addType == "append") {
                            $("#" + options.parentDivId).html($("#" + options.parentDivId).html() + lczalerthtml);
                        }
                        else {
                            $("#" + options.parentDivId).html(lczalerthtml);
                        }
                        if ($(document.body).scrollTop() > $("#" + options.parentDivId).outerHeight(true))
                            $("html,body").animate({ scrollTop: $("#" + options.parentDivId).offset().top }, 150);
                        if (typeof (options.callBackFunction) === "function")
                            setTimeout(options.callBackFunction, 300);
                        break;
                    case 'art':
                        if (_dialog === undefined) {
                            if (options.content != "")
                            _alert(options.content);
                            if (typeof (options.callBackFunction) === "function")
                                options.callBackFunction.apply();
                        }
                        else {
                            options.artOptions.id = options.id;
                            options.artOptions.content = options.content;
                            d = _dialog(options.artOptions);
                            if (typeof (options.callBackFunction) === "function") {
                                d.addEventListener('close', options.callBackFunction);
                            }
                            d.showModal();
                        }
                        break;
                    default:
                        if (options.content != "")
                        _alert(options.content);
                        if (typeof (options.callBackFunction) === "function")
                            options.callBackFunction.apply();
                        break;
                }
            };
            if (ok) {
                this.show();
            }
            _count++;
            alertDialog.oncreate(this);
            return this;
        };

        alertDialog.oncreate = $.noop;
        /** 获取最顶层的对话框API */
        alertDialog.getCurrent = function () {
            return Popup.current;
        };
        /**
        * 根据 ID 获取某对话框 API
        * @param    {String}    对话框 ID
        * @return   {Object}    对话框 API (实例)
        */
        alertDialog.get = function (id) {
            return id === undefined
    ? alertDialog.list
    : alertDialog.list[id];
        };
        alertDialog.list = {};
        /**
        * 默认配置
        */
        alertDialog.defaults = defaults;
        return alertDialog;

    });
    //拦截传统弹出框
    _alert = alert;
    //拦截artdialog 的 dialog对象
    if (!typeof (dialog) == "undefined")
        _dialog = dialog;
    else
        _dialog = undefined;
    window.alert = function (options, ok) {
        return require("lczAlert")(options, ok);
    }
})();