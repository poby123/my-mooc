const styles = {
  container: {
    backgroundColor: 'white',
    marginTop: '0.3em',
    marginLeft: '0.5em',
    marginBottom: '0.5em',
    marginRight: '0.5em',
    fontFamily: 'Nanum Gothic',
  },
  writerContainer: {
    flexDirection: 'row',
    marginTop: '1em',
    paddingLeft: '0.5em',
  },
  writerSubContainer: {
    flexDirection: 'column',
    justifyContent: 'center',
  },
  profileImage: {
    width: '50px',
    height: '50px',
    borderRadius: '50%',
    marginRight: '1em',
  },
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
    // borderColor: 'blue',
    // borderWidth: 1,
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
    border: '1px solid red',
  },
};

export default styles;
