import React, { ChangeEvent, useState } from 'react';
//import logo from './logo.svg';
import './App.css';
import InputBox from 'components/InputBox';

function App() {

  const [email, setEmail] = useState<string>('');

  const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const {value} = event.target;
    setEmail(value);
  }

  const onEmailButtonClickHandler = () => {

  }

  return (
    <>
      <InputBox title='이메일' placeholder='이메일 앞부분만 입력해주세요' 
      type='text' value={email} onChange={onEmailChangeHandler}
      buttonTitle='중복 확인' onButtonClick={onEmailButtonClickHandler}
      message='사용 가능한 이메일 입니다.'/>
    </>
  );
}

export default App;
