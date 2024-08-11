
export interface CarResponse {
    id: string,
    model: string,
    manufacturerName: string
    versions: VersionResponse []
}

interface VersionResponse {
    type: Type,
    productionYear: number
}

enum Type {
    COUPE,
    HATCHBACK,
    LIMOUSINE,
    MINIVAN,
    CABRIOLET,
    SEDAN,
    ROADSTER
}

