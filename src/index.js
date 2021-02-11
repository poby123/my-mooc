import { AppRegistry } from 'react-native';
import App from './App';
import * as React from 'react';

export default function Main() {
  return <App />;
}

AppRegistry.registerComponent('App', () => Main);

AppRegistry.runApplication('App', {
  rootTag: document.getElementById('root'),
});
