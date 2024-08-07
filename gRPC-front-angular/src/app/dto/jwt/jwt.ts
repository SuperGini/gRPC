export interface JwtResponse {
    access_token: string,
    refresh_token: string,
    id_token: string,
    scope: string,
    expires_in: number,
    token_type: string,
}

export interface JwtRequest {
    client_id: string,
    redirect_uri: string,
    grant_type: string,
    code: string,
    code_verifier: string
}
