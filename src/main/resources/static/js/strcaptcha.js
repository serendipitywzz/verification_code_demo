// 高度、宽度、字符大小、字符集
var w = 80;
var h = 24;
var fontSize = h - 2;
var str = "0123456789abcdefABCDEF";

// 随机生成最大值不超过max的整数
function randInt(max) {
    return Math.floor(Math.random() * 100000 % max);
}

// 生成随机长度的字符串验证码
function randCode(len) {
    if (len < 4) {
        len = 4;
    }
    var code = "";
    for (var i = 0; i < len; i++) {
        code += str.charAt(randInt(str.length));
    }
    return code;
}

// 生成随机颜色的方法
function randColor() {
    var r = randInt(256);
    var g = randInt(256);
    var b = randInt(256);
    return "rgb(" + r + "," + g + "," + b + ")";
}

// 生成随机图片的方法
function drawcode(canvas) {
    var valicode = randCode(4);
    w = 5 + fontSize * valicode.length;
    if (canvas != null && canvas.getContext && canvas.getContext("2d")) {
        // 设置显示区域大小
        canvas.style.width = w;
        // 设置画板的高度
        canvas.setAttribute("width", w);
        canvas.setAttribute("height", h);
        // 得到画笔
        var pen = canvas.getContext("2d");
        // 绘制背景
        pen.fillStyle = "rgb(255, 255, 255)";
        pen.fillRect(0, 0, w, h);
        // 设置水平线位置
        pen.textBaseline = "top"; // middle,bottom
        // 绘制内容
        for (var i = 0; i < valicode.length; i++) {
            pen.fillStyle = randColor();
            pen.font = "bold " + (fontSize + randInt(3)) + "px 微软雅黑";
            pen.fillText(valicode.charAt(i), 5 + fontSize * i, randInt(5));
        }
        // 绘制噪音线
        for (var i = 0; i < 2; i++) {
            pen.moveTo(randInt(w) / 2, randInt(h)); // 设置起点
            pen.lineTo(randInt(w), randInt(h)); // 设置终点
            pen.strokeStyle = randColor(); // 设置颜色
            pen.lineWidth = randInt(3); // 设置粗细
            pen.stroke();
        }
        return valicode;
    }
}

var valicode; // 全局变量
function changeCode02() {
    var cvs = document.getElementById("cvs");
    valicode = drawcode(cvs);
}

// 验证
function valiCodeFunc() {
    var code = document.getElementById("inCode02").value;
    //window.alert("valicode: " + valicode + "inCode02: " + code);
    if (code.toLowerCase() === valicode.toLowerCase()) {
        return true;
    } else {
        document.getElementById("err").innerHTML = "验证码输入有误！请重新输入！";
        changeCode02();
        return false;
    }
}

window.onload = changeCode02;
