import React from 'react';
import {View, Text, TextInput, StyleSheet, Button} from 'react-native';
import Theme from '../Theme';
import SpacingComponent from './SpacingComponent';

import {connect} from 'react-redux';
import {signup} from '../actions/auth';

const styles = StyleSheet.create(Theme);

class SignupScreen extends React.PureComponent {
  constructor(props) {
    super(props);
    console.log(this.props);
    this.state = {
      id: '',
      password: '',
      passwordCheck : ''
    };
  }

  render() {
    return (
      <View style={styles.signinContainer}>
        <View style={styles.signinSubContainer}>
          <SpacingComponent spacing={4} />
          <Text style={styles.signinTitle}>SIGN UP</Text>
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
          <SpacingComponent />
          <TextInput
            value={this.state.password}
            onChangeText={(passwordCheck) => this.setState({passwordCheck})}
            placeholder="Password 확인"
            style={styles.signinInput}
          />
          <SpacingComponent spacing={4} />
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

const mapDispatchToProps = () => {
  return {
    signup,
  };
};

export default connect(mapStateToProps, mapDispatchToProps())(SignupScreen);
