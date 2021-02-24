/**
 * Metro configuration for React Native
 * https://github.com/facebook/react-native
 *
 * @format
 */

module.exports = {
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: false,
      },
    }),
  },
  resolver: {
    // 하지 않으면 경로를 찾지못하는 오류 발생
    sourceExts: ['jsx', 'js', 'json', 'ts', 'tsx'],
  },
};
