const styles = {
  /* 전체 컨테이너 */
  container: {
    backgroundColor: 'white',
    marginTop: '0.3em',
    marginLeft: '0.5em',
    marginBottom: '0.5em',
    marginRight: '0.5em',
    fontFamily: 'Nanum Gothic',
  },

  /* 글쓴이 컨테이너 */
  writerContainer: {
    marginTop: '1em',
    paddingLeft: '0.5em',
    paddingRight: '0.5em',
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  writerSubContainer: {
    flexDirection: 'column',
    justifyContent: 'center',
  },
  profileImage: {
    width: '50px',
    height: '50px',
    borderRadius: 1000,
    marginRight: '1em',
  },

  /* 글 관련 스타일 */
  contentContainer: {
    flexDirection: 'column',
    paddingTop: '0.5em',
    paddingRight: '0.5em',
    paddingBottom: '0.5em',
    paddingLeft: '0.5em',
  },
  contentContent: {
    fontFamily: 'inherit',
    marginTop: '0.5em',
  },
  contentLoveContainer: {
    alignItems: 'center',
    flexDirection: 'row',
    color: 'red',
  },
  loveButton: {
    width: '50px',
  },
  commentInputContainer: {
    backgroundColor: '#FFFFFF',
    width: '100%',
    flexDirection: 'row',
    alignItems: 'center',
  },
  commentInput: {
    borderColor: 'gray',
    borderWidth: 1,
    height: 40,
    paddingLeft: '1em',
    paddingRight: '1em',
    flex: 8,
  },
  commentPostButton: {
    borderWidth: 1,
    borderColor: 'red',
    borderStyle: 'solid',
    // : '1px solid red',
  },

  /* 포스팅 글쓰기 관련 스타일 */
  /* 글쓰기 컨테이너 */
  postContainer: {
    marginTop: '0.3em',
    marginLeft: '0.5em',
    marginBottom: '0.5em',
    marginRight: '0.5em',
    fontFamily: 'Nanum Gothic',
    flexDirection: 'column',
  },
  postSubContainer: {
    borderRadius: 5,
    backgroundColor: '#FFFFFF',
    flexDirection: 'row',
    alignItems: 'center',
    borderWidth: 1,
    borderColor: 'gray',
    borderStyle: 'solid',
    padding: '1em',
  },

  /* 카테고리 선택 스타일 */
  postSelect: {
    color: '#FF0000',
  },
  postLabel: {
    flex: 5,
  },

  /* 입력란 스타일 */
  postInput: {
    borderRadius: 5,
    borderWidth: 1,
    borderColor: 'gray',
    borderStyle: 'solid',
    padding: '1em',
    backgroundColor: '#FFFFFF',
    height: '50vh',
    flexDirection: 'row',
    alignItems: 'center',
  },
  postButton: {},

  /* 모달 스타일 */
  modal: {
    alignItems: 'center',
    padding: '1em',
    width: '100%',
    minHeight: '60vh',
    position: 'absolute',
    bottom: 0,
    backgroundColor: '#FFFFFF',
    borderWidth: 1,
    borderColor: 'gray',
    borderStyle: 'solid',
  },

  /* 로그인 관련 */
  /* 로그인 컨테이너 */
  signinContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },

  signinSubContainer: {
    width: '75%',
    borderRadius: '1em',
    fontFamily: 'Nanum Gothic',
    backgroundColor: 'white',
  },

  signinTitle: {
    marginLeft: 'auto',
    marginRight: 'auto',
    fontSize: 20,
  },

  signinInput: {
    width: '50%',
    marginLeft: 'auto',
    marginRight: 'auto',
    borderBottomColor: 'black',
    borderBottomWidth: 1,
    padding: '0.5em',
  },

  signinButtonWrapper: {
    marginLeft: 'auto',
    marginRight: 'auto',
    width: '50%',
  },
};

export default styles;
