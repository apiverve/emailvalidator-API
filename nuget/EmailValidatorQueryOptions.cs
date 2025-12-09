using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.EmailValidator
{
    /// <summary>
    /// Query options for the Email Validator API
    /// </summary>
    public class EmailValidatorQueryOptions
    {
        /// <summary>
        /// The email address to validate (e.g., support@myspace.com)
        /// Example: support@myspace.com
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; }
    }
}
