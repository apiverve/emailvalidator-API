declare module '@apiverve/emailvalidator' {
  export interface emailvalidatorOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface emailvalidatorResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class emailvalidatorWrapper {
    constructor(options: emailvalidatorOptions);

    execute(callback: (error: any, data: emailvalidatorResponse | null) => void): Promise<emailvalidatorResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: emailvalidatorResponse | null) => void): Promise<emailvalidatorResponse>;
    execute(query?: Record<string, any>): Promise<emailvalidatorResponse>;
  }
}
