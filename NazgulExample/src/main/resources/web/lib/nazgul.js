'use strict'
/**
 * 本SDK依赖于jQuery，推荐1.11版本。
 *
 * 初始化样例：
 * 本域绝对路径：
 * nazSvcName = cyan_svc_nazgul($).setSvcBase("/svcname")
 * 跨域绝对路径：
 * nazSvcName = cyan_svc_nazgul($).setSvcBase("http://domain/svcname")
 *
 * @param $ 注入jQuery依赖
 * @param exports 使用追加方式添加函数，例如 cyan_svc_nazgul($, document), 则所有的export函数可以直接调用。
 *
 * @returns {*}
 */
function cyan_svc_nazgul($, exports) {
    if (undefined === exports) {
        exports = {};
    }
    /*========== Constant ==========*/
    var SVC_BASE = "";
    var DEBUG_PORT = 63342;
    var m_isDebug = false;
    if (window.location.port == DEBUG_PORT) {
        SVC_BASE = "http://wxzhangzl.ter.ecoho.cn:8080";
        m_isDebug = true;
    }
    /* API Path */

    /* */
    var SEC_OP_RESET_PASSWORD = "SEC_OP_RESET_PASSWORD";
    /*========== Properties ==========*/


    /*========== Constructor ==========*/
    function init() {

    }

    /*========== Assistant Function ==========*/
    function getAccessToken() {
        //if (undefined !== m_uc_sessionMeta && null !== m_uc_sessionMeta)
        //    if (null !== m_uc_sessionMeta.accessToken && undefined !== m_uc_sessionMeta.accessToken) {
        //        return m_uc_sessionMeta.accessToken;
        //    }
        return "";
    }

    var ajax = {
        invokeApi: function (action, apiUrl, jsonData, callback) {
            console.debug(apiUrl);
            var request = {
                type: action,
                url: apiUrl,
                contentType: "application/json",
                data: JSON.stringify(jsonData),
                beforeSend: function (request) {
                    request.setRequestHeader("accessToken", getAccessToken());
                },
                success: function (resp) {
                    console.debug(resp);
                    if (undefined !== callback) {
                        callback(resp);
                    }
                }
            };
            $.ajax(request);
        },

        post: function (url, jsonData, callback) {
            this.invokeApi("POST", url, jsonData, callback);
        },

        get: function (url, jsonData, callback) {
            this.invokeApi("GET", url, jsonData, callback);
        },

        put: function (url, jsonData, callback) {
            this.invokeApi("PUT", url, jsonData, callback);
        },

        delete: function (url, jsonData, callback) {
            this.invokeApi("DELETE", url, jsonData, callback);
        },
    }
    /*========== Export Function ==========*/
    exports.ajax = ajax;
    exports.setSvcBase = function (url) {
        SVC_BASE = url;
        return exports;
    }

    exports.getApiUrl = function (path, pathVars, params) {
        /*===== Form URL =====*/
        var retUrl = SVC_BASE + path;
        /*===== Replace Path =====*/
        for (var key in pathVars) {
            var parttern = '{' + key + '}';
            var value = pathVars[key];
            retUrl = retUrl.replace(parttern, value);
        }

        /*===== Build Query Parameters =====*/
        if (undefined === params || null == params) {
            return retUrl;
        } else {
            var paramStr = $.param(params);
            return retUrl + '?' + paramStr;
        }
    }

    exports.getQueryParams = function () {
        var args = new Object();
        var query = location.search.substring(1);//获取查询串
        var pairs = query.split("&");//在逗号处断开
        for (var i = 0; i < pairs.length; i++) {
            var pos = pairs[i].indexOf('=');//查找name=value
            if (pos == -1)   continue;//如果没有找到就跳过
            var argname = pairs[i].substring(0, pos);//提取name
            var value = pairs[i].substring(pos + 1);//提取value
            args[argname] = unescape(value);//存为属性
        }
        return args;
    }
    /**
     *
     * @param name
     * @returns {null}
     */
    exports.getQueryString = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
    /**
     * 转换UNIX时间戳为本地格式
     * @param timestamp
     * @returns {string}
     */
    exports.getLocalTime = function (timestamp) {
        return new Date(parseInt(timestamp)).toLocaleString().replace(/:\d{1,2}$/, ' ');
    }

    exports.isWeixinBrowser = function () {
        if (m_isDebug) {
            return true;
        }
        var ua = window.navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            return true;
        } else {
            return false;
        }
    }

    exports.redirect = function (url, pathVars, params) {
        var retUrl = url;
        /*===== Old Params =====*/
        var queryParams = exports.getQueryParams();
        /*===== Replace Path =====*/
        for (var key in pathVars) {
            var parttern = '{' + key + '}';
            var value = pathVars[key];
            retUrl = retUrl.replace(parttern, value);
        }
        /*===== Merge Query Params =====*/
        for (var key in params) {
            queryParams[key] = params[key];
        }
        /*===== Build Query Parameters =====*/
        if (queryParams) {
            var paramStr = $.param(queryParams);
            retUrl = retUrl + '?' + paramStr;
        }
        window.location.href = retUrl;
    }

    /*==========  ==========*/
    init();
    return exports;
};