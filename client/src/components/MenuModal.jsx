import React from 'react';
import { IconButton } from '@material-ui/core';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import CloseIcon from '@material-ui/icons/Close';
import { View, StyleSheet, Modal } from 'react-native';
import Theme from '../Theme';

const styles = StyleSheet.create(Theme);

export default class MenuModal extends React.PureComponent {
  state = {
    isVisible: false,
  };
  constructor(props) {
    super(props);
    this.setIsVisible = this.setIsVisible.bind(this);
  }
  setIsVisible(visible) {
    this.setState({ isVisible: visible });
  }
  render() {
    return (
      <>
        <IconButton onClick={() => this.setIsVisible(true)}>
          <MoreVertIcon />
        </IconButton>
        <Modal
          visible={this.state.isVisible}
          animationType={'slide'}
          onRequestClose={() => this.setIsVisible(false)}
          transparent
        >
          <View style={styles.modal}>
            <IconButton onClick={() => this.setState({ isVisible: false })}>
              <CloseIcon />
            </IconButton>
          </View>
        </Modal>
      </>
    );
  }
}
