package com.acme.validation

import com.acme.rest.ValidLimit
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class LimitValidator : ConstraintValidator<ValidLimit, Int> {
    override fun isValid(value: Int?, context: ConstraintValidatorContext?): Boolean {
        return when {
            value == null -> false
            value > 0 -> true
            else -> false
        }
    }
}