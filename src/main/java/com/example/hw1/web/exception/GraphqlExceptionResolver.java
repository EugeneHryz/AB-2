package com.example.hw1.web.exception;

import com.example.hw1.service.exception.EntityNotFoundException;
import com.example.hw1.service.exception.UnprocessableEntityException;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphqlExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorClassification errorType;

        if (ex instanceof EntityNotFoundException) {
            errorType = ErrorType.NOT_FOUND;
        } else if (ex instanceof UnprocessableEntityException) {
            errorType = ErrorType.BAD_REQUEST;
        } else {
            errorType = ErrorType.INTERNAL_ERROR;
        }
        return GraphqlErrorBuilder.newError()
                .errorType(errorType)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}
