function validate() {

    $('#gymSessionForm').bootstrapValidator({
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            date: {
                message: 'Date is not valid',
                validators: {
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'Date is must be provided in the format DD/MM/YYYY'
                    }
                }
            },
            duration: {
                message: 'Duration is not valid',
                validators: {
                    notEmpty: {
                        message: 'Duration is required and cannot be empty'
                    }
                }
            },
            userWeight: {
                message: 'UserWeight is not valid',
                validators: {
                    notEmpty: {
                        message: 'UserWeight is required and cannot be empty'
                    }
                }
            },
            'exerciseCardio[0].exercise': {
                message: 'Cardio Exercise is not valid',
                validators: {
                    notEmpty: {
                        message: 'Cardio Exercise is required and cannot be empty'
                    }
                }
            },
            'exerciseCardio[0].duration': {
                message: 'Cardio Duration is not valid',
                validators: {
                    notEmpty: {
                        message: 'Cardio Duration is required and cannot be empty'
                    }
                }
            },
            'exerciseCardio[0].distance': {
                message: 'Cardio Distance is not valid',
                validators: {
                    notEmpty: {
                        message: 'Cardio Distance is required and cannot be empty'
                    },
                    digits: {
                        message: 'Cardio Distance must only contain numerical values'
                    }
                }
            },
            'exerciseCardio[0].level': {
                message: 'Cardio Level is not valid',
                validators: {
                    notEmpty: {
                        message: 'Level is required and cannot be empty'
                    },
                    digits: {
                        message: 'Level must only contain numerical values'
                    },
                    between: {
                        min: 1,
                        max: 20,
                        message: 'Level must be a value between 1 - 20'
                    }
                }
            },
            'exerciseCardio[0].calories': {
                message: 'Cardio Calories is not valid',
                validators: {
                    notEmpty: {
                        message: 'Cardio Calories is required and cannot be empty'
                    },
                    digits: {
                        message: 'Cardio Calories must only contain numerical values'
                    }
                }
            }
        }
    });

}