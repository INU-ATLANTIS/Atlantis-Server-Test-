import { ChangeEvent, forwardRef, KeyboardEvent } from 'react';
import './style.css';

interface Props {
    title: string;
    placeholder: string;
    type: 'text' | 'password';
    value: string;
    message?: string;
    isErrorMessgae?: boolean; 
    buttonTitle?: string;
    onChange: (event: ChangeEvent<HTMLInputElement>) => void;
    onKeyDown?: (event: KeyboardEvent<HTMLInputElement>) => void; 
    onButtonClick?: () => void;
}

const InputBox = forwardRef<HTMLInputElement, Props>((props, ref) => {
    const {
        title,
        placeholder,
        type,
        value,
        message,
        isErrorMessgae, 
        buttonTitle,
        onChange,
        onKeyDown, 
        onButtonClick
    } = props;

    const buttonClass = value === '' ? 'input-box-button-disable' : 'input-box-button';
    const messageClass = isErrorMessgae ? 'input-box-message-error' : 'input-box-message'; 

    return (
        <div className='input-box'>
            <div className='input-box-title'>{title}</div>
            <div className='input-box-content'>
                <div className='input-box-body'>
                    <input 
                        ref={ref} 
                        className='input-box-input' 
                        placeholder={placeholder} 
                        type={type} 
                        value={value} 
                        onChange={onChange} 
                        onKeyDown={onKeyDown} 
                    />
                    <div className='input-box-inuemail'>{'@inu.ac.kr'}</div>
                    {buttonTitle !== undefined && onButtonClick !== undefined 
                    && <div className={buttonClass} onClick={onButtonClick}>{buttonTitle}</div>}
                </div>
                {message !== undefined && <div className={messageClass}>{message}</div> }
        
            </div>
        </div>
    );
});

export default InputBox;
