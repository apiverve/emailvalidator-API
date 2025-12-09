declare module '@apiverve/emailvalidator' {
  export interface emailvalidatorOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface emailvalidatorResponse {
    status: string;
    error: string | null;
    data: EmailValidatorData;
    code?: number;
  }


  interface EmailValidatorData {
      creationDate:   null;
      domain:         string;
      email:          string;
      username:       string;
      canConnect:     boolean;
      hasTypo:        boolean;
      isValid:        boolean;
      isMXValid:      boolean;
      isSMTPValid:    boolean;
      isRegexValid:   boolean;
      smtp:           SMTP;
      isCompanyEmail: boolean;
      isFreeEmail:    boolean;
      checksum:       number;
  }
  
  interface SMTP {
      valid:  boolean;
      reason: string;
  }

  export default class emailvalidatorWrapper {
    constructor(options: emailvalidatorOptions);

    execute(callback: (error: any, data: emailvalidatorResponse | null) => void): Promise<emailvalidatorResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: emailvalidatorResponse | null) => void): Promise<emailvalidatorResponse>;
    execute(query?: Record<string, any>): Promise<emailvalidatorResponse>;
  }
}
