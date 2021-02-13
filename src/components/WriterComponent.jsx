import React from 'react';
import PropTypes from 'prop-types';
import { Text, View, Image, StyleSheet } from 'react-native';
import MenuModal from './MenuModal';
import Theme from '../Theme';

const styles = StyleSheet.create(Theme);

export default class WriterComponent extends React.PureComponent {
  constructor(props) {
    super(props);
    this.props = props;
  }

  render() {
    const { image, name, date } = this.props;
    return (
      <View style={styles.writerContainer}>
        <View style={{ flexDirection: 'row' }}>
          <Image style={styles.profileImage} source={{ uri: image }} />
          <View style={styles.writerSubContainer}>
            <Text>{name}</Text>
            <Text>{date}</Text>
          </View>
        </View>
        <MenuModal />
      </View>
    );
  }
}

WriterComponent.propTypes = {
  image: PropTypes.string,
  name: PropTypes.string,
  date: PropTypes.string,
};
