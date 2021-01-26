import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('Should GET Trading Company by ticker')
    request {
        method GET()
        url '/quotes'
        headers {
            accept('application/x-ndjson')
        }
    }

    response {
        status OK()
        headers {
            contentType('application/x-ndjson')
        }
        body(
                [
                        ticker : anyAlphaNumeric(),
                        price  : regex('\\d+\\.*\\d*'),
                        instant: anyIso8601WithOffset()
                ]
        )
    }
}
