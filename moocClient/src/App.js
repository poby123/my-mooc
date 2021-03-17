import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import AuthMenus from './menus/AuthMenus';
import { Provider } from 'react-redux';
import Store from './store'

export default class App extends React.PureComponent {
  store = Store;

  constructor(props) {
    super(props);
    this.store = Store;
  }
  render() {
    return (
      <NavigationContainer>
        <Provider store={this.store}>
          <AuthMenus />
        </Provider>
      </NavigationContainer>
    );
  }
}

