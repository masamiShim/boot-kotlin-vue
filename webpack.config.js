const path = require('path')
const {VueLoaderPlugin} = require('vue-loader')
// cssをjsにバンドルしない
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const ESLintPlugin = require('eslint-webpack-plugin');

module.exports = () => {
    // TODO: 環境毎で使い分けたい
    // 難読化入れる？
    return {
        mode: 'development',
        cache: false,
        entry: {
            vendor: [
                'vue',
                'axios',
                './src/main/client/css/style.css'
            ],
            service: './src/main/client/js/views/service.ts'
        },
        output: {
            path: path.join(__dirname, '/src/main/resources/static'),
            // SpringBootのコンテキストパスと一致させる必要あり
            publicPath: "/",
            filename: "js/[name].bundle.js"
        },
        optimization: {
            splitChunks: {
                chunks: 'async',
                minSize: 20000,
                minRemainingSize: 0,
                minChunks: 1,
                maxAsyncRequests: 30,
                maxInitialRequests: 30,
                enforceSizeThreshold: 50000,
                cacheGroups: {
                    /*
                    commons: {
                        test: /[\\/]node_modules[\\/]/,
                        // cacheGroupKey here is `commons` as the key of the cacheGroup
                        name(module, chunks, cacheGroupKey) {
                            const moduleFileName = module
                                .identifier()
                                .split('/')
                                .reduceRight((item) => item);
                            const allChunksNames = chunks.map((item) => item.name).join('~');
                            return `${cacheGroupKey}-${allChunksNames}-${moduleFileName}`;
                        },
                        chunks: 'all',
                    },
                    */
                    defaultVendors: {
                        filename: (pathData) => {
                            // Use pathData object for generating filename string based on your requirements
                            return `${pathData.chunk.name}-bundle.js`;
                        },
                    },
                },
            }
        },
        plugins: [
            new ESLintPlugin({

                extensions: ['.ts', '.js'],
                exclude: ['node_modules', './src/main/static/js'],
                fix: true
            }),
            new VueLoaderPlugin(),
            new MiniCssExtractPlugin({
                filename: 'css/[name].bundle.css'
            })
        ],
        module: {
            rules: [
                // .jsはトランスパイル
                {
                    test: /\.js$/,
                    exclude: /node_modules/,
                    use: [{
                        loader: "babel-loader",
                        options: {
                            presets: ['@babel/preset-env']
                        }
                    }]
                },
                // .vue コンポーネントをビルドしてバンドル
                {
                    test: /\.vue$/,
                    loader: 'vue-loader'
                },
                // tsのトランスパイル --->
                {
                    test: /\.ts$/,
                    use: [{
                        loader: 'ts-loader',
                        options: {
                            appendTsSuffixTo: [/\.vue$/]
                        }
                    }]
                }, {
                    test: /\.(sa|c)ss$/,
                    use: [
                        // 各loaderは下から順に適用
                        {
                            loader: MiniCssExtractPlugin.loader,
                            options: {
                                publicPath: '/css',
                            },
                        },
                        // .css内の URL パスをpublicPathに合わせてくれる
                        'css-loader',
                        // .sassのビルド
                        'sass-loader',
                    ]
                },
                // 画像ファイルは imagesに出力
                {
                    test: /\.(png|jpg|svg)$/,
                    use: [{
                        loader: "file-loader",
                        options: {
                            name: "[name].[ext]",
                            outputPath: "images/",
                            publicPath: "/images"
                        }
                    }]
                }
            ]
        },
        resolve: {
            extensions: ['.js', '.vue'],
            modules: [
                "node_modules"
            ],
            alias: {
                // vue3からaliasの設定は不要らしい
                '~': path.join(__dirname, 'src', 'main', 'client'),
                '@': path.join(__dirname, 'src', 'main', 'client', 'js'),
                '@@': path.join(__dirname, 'src', 'main', 'client', 'js', 'components')
            }
        }
    }
}
