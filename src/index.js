import { AppRegistry } from 'react-native';
import App from './App';
import * as React from 'react';
import { MuiThemeProvider } from '@material-ui/core/styles';
import { Theme } from './Theme';

export default function Main() {
  return (
    <MuiThemeProvider theme={Theme}>
      <App />
    </MuiThemeProvider>
  );
}

AppRegistry.registerComponent('App', () => Main);

AppRegistry.runApplication('App', {
  rootTag: document.getElementById('root'),
});
