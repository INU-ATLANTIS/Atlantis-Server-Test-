enum ResponseMessage {
    SUCCESS = 'Success.',

    VALIDATION_FAIL = 'Validation failed.',
    
    DUPLICATE_EMAIL = 'Duplicate email.',

    SIGN_IN_FAIL = 'Login information mismatch.',
    
    CERTIFICATION_FAIL = 'Certification failed.',

    MAIL_FAIL = 'Mail send failed.',
    DATABASE_ERROR = 'Database error.',
}

export default ResponseMessage;