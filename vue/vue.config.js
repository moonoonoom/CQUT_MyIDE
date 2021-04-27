module.exports = {
    devServer: {
        port: 9527,     // 端口号
        proxy: {
			"/api": {
				target: "http://localhost:8080", //设置调用的接口域名和端口
				changeOrigin: true, //是否跨域
				pathRewrite: {
					"^/api": ""
				}
			}
		}
    }
};