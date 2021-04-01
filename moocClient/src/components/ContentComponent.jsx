import React from 'react';
import PropTypes from 'prop-types';
import { Text, View, Image, StyleSheet} from 'react-native';
import Swiper from 'react-native-web-swiper'
import Theme from '../Theme';

const styles = StyleSheet.create(Theme);

export default class ContentComponent extends React.PureComponent {
  constructor(props) {
    super(props);
    this.props = props;
  }

  render() {
    const { image, content } = this.props;
    const imageItem = image && image.map(item => {
      return {image: item};
    });
    const renderItem = image && image.map(item=>{
      return (
        <View style={styles.contentImageContainer} key={item}>
          <Image source={item} style={styles.contentImage}/>
        </View>
      );
    })

    return (
      <View style={styles.contentContainer}>
        {image && (
          <View style={styles.swiperContainer}>
            <Swiper>
              {renderItem}
            </Swiper>
          </View>
        )}
        <Text style={styles.contentContent}>{content}</Text>
      </View>
    );
  }
}

ContentComponent.propTypes = {
  image : PropTypes.array,
  content : PropTypes.string
}