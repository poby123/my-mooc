const path = require('path');
const HTMLWebpackPlugin = require('html-webpack-plugin');

const HTMLWebpackPluginConfig = new HTMLWebpackPlugin({
  template: path.resolve(__dirname, './public/index.html'),
  filename: 'index.html',
  inject: 'body',
});

module.exports = {
  entry: {
    // babel-polyfill은 런타임시 현재 브라우저에서 지원하지 함수를 찾아서, object에 prototype을 붙여주는 역할을 한다.
    // babel은 컴파일 타임에 실행되고, babel-polyfill은 런타임시에 실행된다.
    app: ['babel-polyfill', './index.web.js'],
  },
  output: {
    filename: 'bundle.js',
    path: path.join(__dirname, '/public/dist'),
  },
  // Enable source map support
  devtool: 'source-map',

  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules\/(?!()\/).*/,
        use: {
          loader: 'babel-loader',
          options: {
            // Disable reading babel configuration
            babelrc: false,
            configFile: false,

            // The configuration for compilation
            presets: [
              ['@babel/preset-env', {useBuiltIns: 'entry'}],
              '@babel/preset-react',
              '@babel/preset-flow',
              '@babel/preset-typescript',
            ],
            plugins: [
              '@babel/plugin-proposal-class-properties',
              '@babel/plugin-proposal-object-rest-spread',
            ],
          },
        },
      },
      {
        test: /\.(jpg|png|woff|woff2|eot|ttf|svg)$/,
        loader: 'file-loader',
      },
    ],
  },
  resolve: {
    alias: {
      'react-native$': 'react-native-web',
    },
    // 파일 확장자 관련
    extensions: ['.webpack.js', '.web.js', '.js', '.jsx', '.json'],
  },
  plugins: [HTMLWebpackPluginConfig],
  devServer: {
    open: false,
    historyApiFallback: true,
    contentBase: './',
    hot: true,
  },
};
