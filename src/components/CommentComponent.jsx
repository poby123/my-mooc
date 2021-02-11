import React from 'react';
import { View, StyleSheet, TextInput } from 'react-native';
import { IconButton } from '@material-ui/core';
import { IoSend } from 'react-icons/io5';
import Theme from '../Theme';

const styles = StyleSheet.create(Theme);

export default class CommentComponent extends React.PureComponent {
  constructor(props) {
    super(props);
  }
  render() {
    const { onChange } = this.props;
    return (
      <View style={styles.commentInputContainer}>
        <TextInput style={styles.commentInput} onChange={onChange} placeholder={'댓글을 입력해주세요...'} />
        <IconButton color={'inherit'} onClick={() => console.log('pressed')}>
          <IoSend />
        </IconButton>
      </View>
    );
  }
}
