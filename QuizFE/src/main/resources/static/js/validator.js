// Custom validator
const Validator = (options) => {
    let selectorRules = {};

    let validate = (inputElement, rule) => {
        let errorElement = inputElement.parentElement.querySelector(options.errorSelector);
        let errorMessage;
        let rules = selectorRules[rule.selector];

        for (let i = 0; i < rules.length; i++) {
            errorMessage = rules[i](inputElement.value);
            if (errorMessage) break;
        }

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            inputElement.classList.remove('is-valid');
            inputElement.classList.add('is-invalid');
        }
        else {
            errorElement.innerText = '';
            inputElement.classList.remove('is-invalid');
            inputElement.classList.add('is-valid');
        }

        return !errorMessage;
    }

    // lấy element của form cần validate
    let formElement = document.querySelector(options.form);
    if (formElement) {
        formElement.onsubmit = e => {
            e.preventDefault();

            let isFormValid = true;

            options.rules.forEach(rule => {
                let inputElement = formElement.querySelector(rule.selector);
                let isValid = !validate(inputElement, rule);
                if (!isValid) {
                    isFormValid = false;
                }
            });

            if (isFormValid) {
                if (typeof options.onsubmit === 'function') {
                    let enableInputs = formElement.querySelectorAll('[name]');
                    let formValues = Array.form(enableInputs).reduce( (value, input) => {
                        return (values[input.name] = input.value) && value;
                    }, {});

                    options.onSubmit(formValues);
                }
            }
        }

        options.rules.forEach(rule => {
            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            }
            else {
                selectorRules[rule.selector] = [rule.test];
            }

            let inputElement = formElement.querySelector(rule.selector);

            if (inputElement) {
                inputElement.onblur = () =>  {
                    validate(inputElement, rule);
                }

                inputElement.oninput = () => {
                    let errorElement = inputElement.parentElement.querySelector(options.errorSelector);

                    errorElement.innerText = '';
                    inputElement.classList.remove('is-invalid');
                    inputElement.classList.add('is-valid');
                }
            }
        });
    }
}

Validator.isRequired = (selector, message) => {
    return {
        selector: selector,
        test: value => value.trim() ? undefined : message ||  'This field is required.'
    }
}


Validator.isEmail = (selector, message) => {
    return {
        selector: selector,
        test: value => {
            let regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : message ||  'Email format is wrong.';
        }
    }
}

Validator.isValidPassword = (selector, message) => {
    return {
        selector: selector,
        test: value => {
            let regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
            return regex.test(value) ? undefined : message ||  'Password must be at least 8 characters, digit, lower, upper and special character.';
        }
    }
}

Validator.isPhone = (selector, message) => {
    return {
        selector: selector,
        test: value => {
            let regex = /^\d{10}$/;
            return regex.test(value) ? undefined : message ||  'Phone number format is wrong.';
        }
    }
}

Validator.minLength = (selector, min, message) => {
    return {
        selector: selector,
        test: value => value.length >= min ? undefined : message || `The minimum length is ${min} characters.`
    }
}

Validator.maxLength = (selector, max, message) => {
    return {
        selector: selector,
        test: value => value.length <= max ? undefined : message ||  `The maximum length is ${max} characters.`
    }
}

Validator.isConfirmed = (selector, getConfirmedValue, message) => {
    return {
        selector : selector,
        test: value => value === getConfirmedValue() ? undefined : message ||  'Password not match.'
    }
}