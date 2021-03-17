import React from 'react';
import {View, Text, TextInput, StyleSheet, Button} from 'react-native';
import Theme from '../Theme';
import SpacingComponent from './SpacingComponent';

import {connect} from 'react-redux';
import {signin, signout, signup} from '../actions/auth';

const styles = StyleSheet.create(Theme);

class SigninScreen extends React.PureComponent {
  constructor(props) {
    super(props);
    console.log(this.props);
    this.state = {
      id: '',
      password: '',
    };
  }

  componentDidMount() {
    this.props
      .signin('test-id', 'test-password')
      .then((result) => {
        console.log('success : ' + result);
      })
      .catch((error) => {
        console.log('error : ' + error);
      });
  }

  render() {
    return (
      <View style={styles.signinContainer}>
        <View style={styles.signinSubContainer}>
          <SpacingComponent spacing={4} />
          <Text style={styles.signinTitle}>LOGIN</Text>
          <SpacingComponent spacing={4} />
          <TextInput
            value={this.state.id}
            onChangeText={(id) => this.setState({id})}
            placeholder="ID"
            style={styles.signinInput}
          />
          <SpacingComponent />
          <TextInput
            value={this.state.password}
            onChangeText={(password) => this.setState({password})}
            placeholder="Password"
            style={styles.signinInput}
          />
          <SpacingComponent spacing={4} />
          <View style={styles.signinButtonWrapper}>
            <Button title={'로그인'} />
          </View>
          <SpacingComponent />
          <View style={styles.signinButtonWrapper}>
            <Button color="coral" title={'회원가입'} />
          </View>
          <SpacingComponent spacing={4} />
        </View>
      </View>
    );
  }
}

const mapStateToProps = (state) => ({
  isLoggedIn: state.isLoggedIn,
  member: state.member,
});

const mapDispatchToProps = (dispatch) => {
  return {
    signin,
    signout,
    signup
  };
};

export default connect(mapStateToProps, mapDispatchToProps())(SigninScreen);
