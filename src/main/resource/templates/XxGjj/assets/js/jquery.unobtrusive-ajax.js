/// <reference path="jquery-1.10.1.js"/*tpa=http://www.xxzfgjj.com/assets/js/jquery-1.10.1.js*/ />

/*!
** Unobtrusive Ajax support library for jQuery
** Copyright (C) Microsoft Corporation. All rights reserved.
*/

/*jslint white: true, browser: true, onevar: true, undef: true, nomen: true, eqeqeq: true, plusplus: true, bitwise: true, regexp: true, newcap: true, immed: true, strict: false */
/*global window: false, jQuery: false */

(function () {//jquery 1.9 以下用function($)
    var data_click = "unobtrusiveAjaxClick",
        data_validation = "unobtrusiveValidation";
    function getFunction(code, argNames) {

        var fn = window, parts = (code || "").split(".");

        while (fn && parts.length) {
            var ss = parts.shift();

            if (ss.indexOf("(") != -1)//动态函数调用 
            {
                fn = fn[ss.substring(0, ss.indexOf("("))];
                var parmer = ss.substring(ss.indexOf("(") + 1, ss.indexOf(")"));
                var parmers = parmer.split(",");
                var snewarguments = [];
                while (parmers.length) {
                    snewarguments.push(eval(parmers.shift()));
                }
                fn = fn.apply(null, snewarguments);
            }
            else {
                fn = fn[ss];
            }

        }

        if (typeof (fn) === "function") {
            return fn;
        }
        argNames.push(code);
        return Function.constructor.apply(null, argNames);
    }

    function isMethodProxySafe(method) {
        return method === "GET" || method === "POST";
    }

    function asyncOnBeforeSend(xhr, method) {
        if (!isMethodProxySafe(method)) {
            xhr.setRequestHeader("X-HTTP-Method-Override", method);
        }
    }

    function asyncOnSuccess(element, data, contentType) {
        var mode;
        if (contentType.indexOf("application/x-javascript") !== -1) {  // jQuery already executes JavaScript for us
            return;
        }

        mode = (element.getAttribute("data-ajax-mode") || "").toUpperCase();
        $(element.getAttribute("data-ajax-update")).each(function (i, update) {
            var top;

            switch (mode) {
                case "BEFORE":
                    top = update.firstChild;
                    $("<div />").html(data).contents().each(function () {
                        update.insertBefore(this, top);
                    });
                    break;
                case "AFTER":
                    $("<div />").html(data).contents().each(function () {
                        update.appendChild(this);
                    });
                    break;
                default:
                    $(update).html(data);
                    break;
            }
        });
    }

    function asyncRequest(element, options) {

        var confirm, loading, method, duration;

        confirm = element.getAttribute("data-ajax-confirm");
        if (confirm && !window.confirm(confirm)) {
            return;
        }

        loading = $(element.getAttribute("data-ajax-loading"));
        duration = element.getAttribute("data-ajax-loading-duration") || 0;

        $.extend(options, {
            type: element.getAttribute("data-ajax-method") || undefined,
            url: element.getAttribute("data-ajax-url") || undefined,
            beforeSend: function (xhr) {
                var result;
                asyncOnBeforeSend(xhr, method);
                /*
                添加额外参数 lcz
                */
                var newarguments = [];
                for (var i = 0; i < arguments.length; i++) {
                    newarguments.push(arguments[i]);
                }
                newarguments.push(element);
                /*
                添加额外参数结束 lcz
                */


                result = getFunction(element.getAttribute("data-ajax-begin"), ["xhr", "ajaxElement"]).apply(this, newarguments);

                if (result !== false) {
                    loading.show(duration);
                }
                return result;
            },
            complete: function () {
                loading.hide(duration);
                /*
                添加额外参数 lcz
                */
                var newarguments = [];
                for (var i = 0; i < arguments.length; i++) {
                    newarguments.push(arguments[i]);
                }
                newarguments.push(element);
                /*
                添加额外参数结束 lcz
                */
                getFunction(element.getAttribute("data-ajax-complete"), ["xhr", "status", "ajaxElement"]).apply(this, newarguments);
            },
            success: function (data, status, xhr) {
                asyncOnSuccess(element, data, xhr.getResponseHeader("Content-Type") || "text/html");
                /*
                添加额外参数 lcz
                */
                var newarguments = [];
                for (var i = 0; i < arguments.length; i++) {
                    newarguments.push(arguments[i]);
                }
                newarguments.push(element);
                /*
                添加额外参数结束 lcz
                */
                getFunction(element.getAttribute("data-ajax-success"), ["data", "status", "xhr", "ajaxElement"]).apply(this, newarguments);
            },
            error: function () {
                /*
                添加额外参数 lcz
                */
                var newarguments = [];
                for (var i = 0; i < arguments.length; i++) {
                    newarguments.push(arguments[i]);
                }
                newarguments.push(element);
                /*
                添加额外参数结束 lcz
                */
                getFunction(element.getAttribute("data-ajax-failure"), ["xhr", "status", "error", "ajaxElement"]).apply(this, newarguments);
            }
        });
        options.data.push({ name: "X-Requested-With", value: "XMLHttpRequest" });

        method = options.type.toUpperCase();
        if (!isMethodProxySafe(method)) {
            options.type = "POST";
            options.data.push({ name: "X-HTTP-Method-Override", value: method });
        }

        $.ajax(options);
    }

    function validate(form) {
        var validationInfo = $(form).data(data_validation);
        return !validationInfo || !validationInfo.validate || validationInfo.validate();
    }

    $("a[data-ajax=true]").on("click", function (evt) {//jquery 1.9 以下用live 以上用on
        evt.preventDefault();
        asyncRequest(this, {
            url: this.href,
            type: "GET",
            data: []
        });
    });

    $("form[data-ajax=true] input[type=image]").on("click", function (evt) {//jquery 1.9 以下用live 以上用on
        var name = evt.target.name,
            $target = $(evt.target),
            form = $target.parents("form")[0],
            offset = $target.offset();

        $(form).data(data_click, [
            { name: name + ".x", value: Math.round(evt.pageX - offset.left) },
            { name: name + ".y", value: Math.round(evt.pageY - offset.top) }
        ]);

        setTimeout(function () {
            $(form).removeData(data_click);
        }, 0);
    });

    $("form[data-ajax=true] :submit").on("click", function (evt) {//jquery 1.9 以下用live 以上用on
        var name = evt.target.name,
            form = $(evt.target).parents("form")[0];
        $(form).data(data_click, name ? [{ name: name, value: evt.target.value}] : []);

        setTimeout(function () {
            $(form).removeData(data_click);
        }, 0);
    });

    $("form[data-ajax=true]").on("submit", function (evt) {//jquery 1.9 以下用live 以上用on
        evt.preventDefault();
        var clickInfo = $(this).data(data_click) || [];
        if (!validate(this)) {
            return false;
        }
        asyncRequest(this, {
            url: this.action,
            type: this.method || "GET",
            data: clickInfo.concat($(this).serializeArray())
        });
    });
} (jQuery));