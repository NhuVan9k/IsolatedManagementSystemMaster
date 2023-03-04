function Validator(options) {
    var formElement = document.querySelector(options.form);

    function validate(inputElement, rule) {
        var errorMessage = rule.test(inputElement.value);
        var errorElement = inputElement.parentElement.querySelector(options.errorSelector);

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        } else {
            errorElement.innerText = '';
            inputElement.parentElement.classList.remove('invalid');
        }
    }

    if (formElement) {
        options.rules.forEach(function (rule) {
            var inputElement = formElement.querySelector(rule.selector);

            if (inputElement) {
                // xử lý trường hợp blur ra khỏi input
                inputElement.onblur = function () {
                    validate(inputElement, rule);
                }

                // xử lý mỗi khi người dùng nhập vào input
                inputElement.oninput = function () {
                    var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
                    errorElement.innerText = '';
                    inputElement.parentElement.classList.remove('invalid');
                }
            }
        });
    }
}

// Định nghĩa rules
// Nguyên tắc của các rules:
// 1. Khi có lỗi => Trả ra message lỗi
// 2. Khi hợp lệ => Không trả về cái gì

Validator.isRequired = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            return value.trim() ? undefined : "Vui lòng nhập trường này"
        }
    };
}

Validator.minLength = function (selector, min1) {
    return {
        selector: selector,
        test: function (value) {
            return value.length >= min1 ? undefined : "Vui lòng nhập tối thiểu " + min1 + " ký tự"
        }
    };
}

Validator.isPhone = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            if ((value.length == 9 || value.length == 10) && !isNaN(value))
                return underfined;
            return "Vui lòng nhập đúng số điện thoại";
        }
    }
}