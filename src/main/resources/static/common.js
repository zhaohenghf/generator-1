/**
 * 工具类
 *
 * @author 宋欢
 */
Common = {
    /**
     * 向控制台输出日志
     */
    log: function (v) {
        if (window.console && window.console.log) {
            window.console.log(v);
        }
    },

    /**
     * 向控制台输出信息
     */
    info: function (v) {
        if (window.console && window.console.info) {
            window.console.info(v);
        }
    },

    /**
     * 输出错误
     */
    error: function (v) {
        if (window.console && window.console.error) {
            window.console.error(v);
        }
    },

    /**
     * 通过id名，获取元素对象
     */
    id: function (element) {
        return typeof (element) == 'object' ? element : document.getElementById(element);
    },

    /**
     * 清除字符串左边空格
     */
    ltrim: function (E) {
        return (E || "").replace(/^\s+/g, "");
    },

    /**
     * 清除字符串右边空格
     */
    rtrim: function (E) {
        return (E || "").replace(/\s+$/g, "");
    },

    /**
     * 清除字符串两边空格
     */
    trim: function (E) {
        return (E || "").replace(/^\s+|\s+$/g, "");
    },

    /**
     * 字符串首字母大写，其他字母小写
     */
    ucFirst: function (E) {
        return E.substr(0, 1).toUpperCase() + E.substr(1).toLowerCase();
    },

    /**
     * 将数组连接成字符串
     */
    join: function (glue, E) {
        if (E.length <= 0) return "";

        let r = "";
        for (let i in E) {
            r += E[i] + glue;
        }

        return r.substr(0, r.length - glue.length);
    },

    /**
     * 获取字符在数组中的位置
     * if v not exists, return -1
     */
    inArr: function (E, v) {
        if (typeof (v) == 'string' || typeof (v) == 'number') {
            for (let i in E) {
                if (E[i] === v) {
                    return i;
                }
            }
        }

        return -1;
    },

    /**
     * 匹配字符串前缀
     */
    startWith: function (E, v) {
        return E.substr(0, v.length) === v;
    },

    /**
     * 匹配字符串后缀
     */
    endWith: function (E, v) {
        let p = E.length - v.length;
        return E.substr(p) === v;
    },

    /**
     * 空字符串？
     */
    isEmpty: function (s) {
        return s === undefined || s === null || s === "";
    },

    /**
     * 整数？
     */
    isInt: function (E) {
        let pattern = /^[0-9]+$/;
        return (E !== "" && pattern.test(E));
    },

    /**
     * 邮箱格式？
     */
    isMail: function (E) {
        let pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        return (E !== "" && pattern.test(E));
    },

    /**
     * 闰年？
     */
    isLeapYear: function (E) {
        return (0 === E % 4 && ((E % 100 !== 0) || (E % 400 === 0)));
    }

};

/**
 * 表单
 *
 * @author 宋欢
 */
FormElement = {
    /**
     * 通过类型和名称，获取首个input
     */
    $: function (type, name) {
        let elements = FormElement.getInput(type, name);
        if (elements !== undefined && elements.length > 0) {
            return elements[0];
        }
    },

    /**
     * 通过类型和名称，获取被选中的input值
     */
    getCheckedValue: function (type, name) {
        let elements = FormElement.getCheckedInput(type, name);
        if (elements === undefined || elements.length === 0) {
            return;
        }

        let result = [];
        let n = 0;

        for (let i in elements) {
            if (elements[i] === undefined) {
                continue;
            }

            result[n++] = elements[i].value;
        }

        return result;
    },

    /**
     * 通过类型和名称，获取被选中的input列表
     */
    getCheckedInput: function (type, name) {
        let elements = FormElement.getInput(type, name);
        if (elements === undefined || elements.length === 0) {
            return;
        }

        let result = [];
        let n = 0;

        for (let i in elements) {
            if (elements[i] === undefined) {
                continue;
            }

            if (elements[i].checked) {
                result[n++] = elements[i];
            }
        }

        return result;
    },

    /**
     * 通过类型和名称，获取input列表
     */
    getInput: function (type, name) {
        if (type === undefined) {
            Common.error("type can not be undefined");
            return;
        }

        if (type === "") {
            Common.error("type can not be empty");
            return;
        }

        if (name === undefined) {
            Common.error("name can not be undefined");
            return;
        }

        if (name === "") {
            Common.error("name can not be empty");
            return;
        }

        let elements = document.getElementsByTagName("input");
        if (elements === undefined || elements.length === 0) {
            return;
        }

        let result = [];
        let n = 0;

        for (let i in elements) {
            if (elements[i] === undefined) {
                continue;
            }

            if (elements[i].type === type && elements[i].name === name) {
                result[n++] = elements[i];
            }
        }

        return result;
    }

};

/**
 * Ajax
 *
 * @author 宋欢
 */
Ajax = {
    /**
     * Status Code OK
     */
    SC_OK: 0,

    /**
     * Method Not Allowed
     */
    METHOD_NOT_ALLOWED: {
        "code": 405,
        "message": "Method Not Allowed"
    },

    /**
     * System Run Error
     */
    SYSTEM_RUN_ERR: {
        "code": 500,
        "message": "System Run Error"
    },

    /**
     * Method Get
     */
    METHOD_GET: "GET",

    /**
     * Method Post
     */
    METHOD_POST: "POST",

    /**
     * Content Type application/json
     */
    CONTENT_TYPE_JSON: "application/json;charset=UTF-8",

    /**
     * Get请求
     *
     * p = {
     *     url: "",
     *     data: JSON,
     *     async: true|false,
     *     success: function(data) {}
     *     error: function(code, message) {}
     * }
     */
    get: function (p) {
        if (typeof (p) == 'object') {
            p.type = Ajax.METHOD_GET;
        }

        Ajax.req(p);
    },

    /**
     * Post请求
     *
     * p = {
     *     url: "",
     *     data: JSON,
     *     async: true|false,
     *     success: function(data) {}
     *     error: function(code, message) {}
     * }
     */
    post: function (p) {
        if (typeof (p) == 'object') {
            p.type = Ajax.METHOD_POST;
        }

        Ajax.req(p);
    },

    /**
     * 请求
     *
     * p = {
     *     type: "GET|POST",
     *     url: "",
     *     data: JSON,
     *     async: true|false,
     *     success: function(data) {}
     *     error: function(code, message) {}
     * }
     */
    req: function (p) {
        if (typeof (p) != 'object') {
            Common.error("Ajax.req()'parameter must be a json object");
            alert("Ajax.req()'parameter must be a json object");
            return;
        }

        if (typeof (p.success) != 'function') {
            Common.error("Ajax.req()'parameter.success must be a function");
            if (typeof (p.error) == 'function') {
                p.error({
                    "code": Ajax.SYSTEM_RUN_ERR.code,
                    "message": "Ajax.req()'parameter.success must be a function"
                });
            } else {
                alert("Ajax.req()'parameter.success must be a function");
            }
            return;
        }

        Ajax.doReq(p.type, p.url, p.data, p.async, function (response) {
            if (response.success === true) {
                return p.success(response.data);
            } else {
                Common.error("doReq failed, code: " + response.code + ", message: " + response.message);
                if (typeof (p.error) == 'function') {
                    p.error(response.code, response.message);
                } else {
                    alert(response.message);
                }
            }
        });
    },

    /**
     * 执行请求
     *
     * @param method    GET | POST
     * @param url       /domain:port/do
     * @param parameter GET: a=1&b=2; POST: {}
     * @param async     true | false
     * @param complete  function(response) {}
     */
    doReq: function (method, url, parameter, async, complete) {
        if (typeof (complete) != 'function') {
            Common.error("complete must be a function, url: " + url + ", parameter: " + parameter);
            alert("complete must be a function");
            return;
        }

        if (Common.isEmpty(method)) {
            method = Ajax.METHOD_GET;
        } else {
            method = method.toUpperCase();
            if (method !== Ajax.METHOD_GET && method !== Ajax.METHOD_POST) {
                Common.error("method must be GET or POST, method: " + method);
                return complete(Ajax.METHOD_NOT_ALLOWED);
            }
        }

        if (Common.isEmpty(url)) {
            Common.error("url can not be empty");
            return complete(Ajax.SYSTEM_RUN_ERR);
        }

        if (method === Ajax.METHOD_GET) {
            url = Ajax.joinParameter(url, parameter);
            parameter = null;
        } else if (method === Ajax.METHOD_POST) {
            parameter = Common.isEmpty(parameter) ? null : JSON.stringify(parameter);
        }

        async !== false ? async = true : undefined;

        let xhrObj = Ajax.newXhr();
        if (xhrObj === undefined || xhrObj === null) {
            Common.error("xhrObj is undefined, new XMLHttpRequest failed");
            return complete(Ajax.SYSTEM_RUN_ERR);
        }

        xhrObj.open(method, url, async);
        if (method === Ajax.METHOD_POST) {
            xhrObj.setRequestHeader("Content-Type", Ajax.CONTENT_TYPE_JSON);
        }

        xhrObj.send(parameter);
        xhrObj.onreadystatechange = function () {
            /**
             * readyState 值
             * 2 已经发送数据了，但是还没接收到反馈
             * 3 收到反馈了，反馈描述数据正在发送的过程中
             * 4 反馈描述数据已经被接收完毕
             */
            if (xhrObj.readyState === 4) {
                if (xhrObj.status === 200) {
                    let responseText = xhrObj.responseText;
                    if (Common.isEmpty(responseText)) {
                        Common.error("responseText can not be empty");
                        return complete(Ajax.SYSTEM_RUN_ERR);
                    }

                    if (responseText.indexOf("{") === -1 || responseText.indexOf("}") === -1) {
                        Common.error("responseText must be a json string, responseText: " + responseText);
                        return complete(Ajax.SYSTEM_RUN_ERR);
                    }

                    eval("responseText = " + responseText + ";");
                    if (typeof (responseText) != 'object') {
                        Common.error("responseText must be a json object, responseText: " + responseText);
                        return complete(Ajax.SYSTEM_RUN_ERR);
                    }

                    return complete(responseText);
                } else {
                    Common.error("xhrObj.status must be 200, xhrObj.status: " + xhrObj.status);
                    return complete(Ajax.SYSTEM_RUN_ERR);
                }
            }
        }
    },

    /**
     * 拼接参数
     */
    joinParameter: function (url, parameter) {
        if (Common.isEmpty(url)) {
            return "?" + parameter;
        }

        if (Common.isEmpty(parameter)) {
            return url;
        }

        if (url.indexOf("?") === -1) {
            return url + "?" + parameter;
        }

        if (Common.endWith(url, "&") ||
            Common.startWith(parameter, "&")) {
            return url + parameter;
        }

        return url + "&" + parameter;
    },

    /**
     * new XMLHttpRequest
     */
    newXhr: function () {
        let xhrObj = undefined;

        try {
            xhrObj = new XMLHttpRequest(); // Firefox IE8和非IE内核
        } catch (e) {
            let kernels = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"]; // IE5.5 IE6 IE7 内核
            for (let i in kernels) {
                try {
                    xhrObj = new ActiveXObject(kernels[i]);
                } catch (e) {
                    continue;
                }
                break;
            }
        }

        return xhrObj;
    }

};
