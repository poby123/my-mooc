import React from 'react';
import { Text, View, Image, StyleSheet } from 'react-native';
import Theme from '../Theme';

const styles = StyleSheet.create(Theme);

export default class ContentComponent extends React.PureComponent {
  constructor(props) {
    super(props);
    this.props = props;
  }

  render() {
    const { image, content } = this.props;
    return (
      <View style={styles.contentContainer}>
        {image && <Image source={{ uri: image }} style={{ minHeight: '300px' }} />}
        <Text style={{ fontFamily: 'inherit' }}>{content}</Text>
      </View>
    );
  }
}
