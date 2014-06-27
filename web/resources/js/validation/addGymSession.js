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
                validators: {
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'Date is must be provided in the format DD/MM/YYYY'
                    }
                }
            },
            duration: {
                validators: {
                    notEmpty: {
                        message: 'Duration is required and cannot be empty'
                    }
                }
            },
            userWeight: {
                validators: {
                    notEmpty: {
                        message: 'UserWeight is required and cannot be empty'
                    }
                }
            },
            'exerciseCardio[0].exercise': {
                validators: {
                    notEmpty: {
                        message: 'Cardio Exercise is required and cannot be empty'
                    }
                }
            },
            'exerciseCardio[0].duration': {
                validators: {
                    notEmpty: {
                        message: 'Cardio Duration is required and cannot be empty'
                    }
                }
            },
            'exerciseCardio[0].distance': {
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
                validators: {
                    notEmpty: {
                        message: 'Cardio Calories is required and cannot be empty'
                    },
                    digits: {
                        message: 'Cardio Calories must only contain numerical values'
                    }
                }
            },
            'exerciseWeight[0].exercise': {
                validators: {
                    notEmpty: {
                        message: 'Weight Exercise is required and cannot be empty'
                    }
                }
            },
            'exerciseWeight[0].reps': {
                validators: {
                    notEmpty: {
                        message: 'Weight Reps is required and cannot be empty'
                    },
                    digits: {
                        message: 'Weight Reps must only contain numerical values'
                    }
                }
            },
            'exerciseWeight[0].weightLifted': {
                validators: {
                    notEmpty: {
                        message: 'Weight Lifted is required and cannot be empty'
                    },
                    digits: {
                        message: 'Weight Lifted must only contain numerical values'
                    }
                }
            }
        }
    });

}