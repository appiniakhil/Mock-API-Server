package org.testsigma.mockserver.enums;

public enum DynamicVariables {
    RANDOM_FULL_NAME("{{$randomFullName1}}", "#{name.Full_name}"),
    RANDOM_FIRST_NAME("{{$randomFirstName1}}", "#{Name.first_name}"),
    RANDOM_LAST_NAME("{{$randomLastName1}}", "#{Name.last_name}"),
    RANDOM_EMAIL("{{$randomEmail1}}", "#{Internet.email_address}"),
    RANDOM_PHONE("{{$randomPhone1}}", "#{PhoneNumber.phone_number}"),
    RANDOM_DIGIT("{{$randomDigit1}}", "#{Number.digit}"),
    NUMBER_BETWEEN("{{$numberBetween}}", "#{Number.number_between}"),
    NUMBER_DIGITS("{{$numberDigits}}", "#{Number.digits}"),
    GUID("{{$guid}}", "#{Internet.uuid}"),
    RANDOM_UUID("{{$randomUUID}}", "#{Internet.uuid}"),
    RANDOM_COLOR("{{$randomColor}}", "#{Color.name}"),
    RANDOM_HEX_COLOR("{{$randomHexColor}}", "#{Color.hex}"),
    RANDOM_IP("{{$randomIP}}", "#{Internet.ipv4_address}"),
    RANDOM_IPV6("{{$randomIPV6}}", "#{Internet.ipv6_address}"),
    RANDOM_MAC_ADDRESS("{{$randomMACAddress}}", "#{Internet.mac_address}"),
    RANDOM_PASSWORD("{{$randomPassword}}", "#{Internet.password}"),
    RANDOM_LOCALE("{{$randomLocale}}", "#{Address.locale}"),
    RANDOM_SEMVER("{{$randomSemver}}", "#{App.version}"),
    RANDOM_NAME_PREFIX("{{$randomNamePrefix}}", "#{Name.prefix}"),
    RANDOM_NAME_SUFFIX("{{$randomNameSuffix}}", "#{Name.suffix}"),
    RANDOM_JOB_AREA("{{$randomJobArea}}", "#{Address.city}"),
    RANDOM_JOB_DESCRIPTOR("{{$randomJobDescriptor}}", "#{Job.position}"),
    RANDOM_JOB_TITLE("{{$randomJobTitle}}", "#{Job.title}"),
    RANDOM_JOB_TYPE("{{$randomJobType}}", "#{Job.field}"),
    RANDOM_PHONE_NUMBER("{{$randomPhoneNumber}}", "#{PhoneNumber.phone_number}"),
    RANDOM_CITY("{{$randomCity}}", "#{Address.city}"),
    RANDOM_STREET_NAME("{{$randomStreetName}}", "#{Address.street_name}"),
    RANDOM_STREET_ADDRESS("{{$randomStreetAddress}}", "#{Address.street_address}"),
    RANDOM_COUNTRY("{{$randomCountry}}", "#{Address.country}"),
    RANDOM_COUNTRY_CODE("{{$randomCountryCode}}", "#{Address.country_code}"),
    RANDOM_LATITUDE("{{$randomLatitude}}", "#{Address.latitude}"),
    RANDOM_LONGITUDE("{{$randomLongitude}}", "#{Address.longitude}"),
    RANDOM_IMAGE_URL("{{$randomImageUrl}}", "#{Internet.image}"),
    RANDOM_BANK_ACCOUNT_NAME("{{$randomBankAccountName}}", "#{Number.digits '8'}"),
    RANDOM_CREDIT_CARD_MASK("{{$randomCreditCardMask}}", "#{Number.digits '4'}"),
    RANDOM_BANK_ACCOUNT_BIC("{{$randomBankAccountBic}}", "#{Finance.bic}"),
    RANDOM_BANK_ACCOUNT_IBAN("{{$randomBankAccountIban}}", "#{Finance.iban}"),
    RANDOM_COMPANY_NAME("{{$randomCompanyName}}", "#{Company.name}"),
    RANDOM_COMPANY_SUFFIX("{{$randomCompanySuffix}}", "#{Company.suffix}"),
    RANDOM_BS("{{$randomBs}}", "#{Company.bs}"),
    RANDOM_BS_BUZZ("{{$randomBsBuzz}}", "#{Company.buzz_word}"),
    RANDOM_CATCH_PHRASE("{{$randomCatchPhrase}}", "#{Company.catch_phrase}"),
    RANDOM_DOMAIN_NAME("{{$randomDomainName}}", "#{Internet.domain_name}"),
    RANDOM_DOMAIN_SUFFIX("{{$randomDomainSuffix}}", "#{Internet.domain_suffix}"),
    RANDOM_DOMAIN_WORD("{{$randomDomainWord}}", "#{Internet.domain_word}"),
    RANDOM_USER_NAME("{{$randomUserName}}", "#{Name.first_name}.#{Name.last_name}"),
    RANDOM_URL("{{$randomUrl}}", "#{Internet.url}"),
    RANDOM_FILE_NAME("{{$randomFileName}}", "#{File.file_name}"),
    RANDOM_FILE_EXT("{{$randomFileExt}}", "#{File.extension}"),
    RANDOM_MIME_TYPE("{{$randomMimeType}}", "#{System.mime_type}"),
    RANDOM_PRICE("{{$randomPrice}}", "#{Commerce.price}"),
    RANDOM_PRODUCT_MATERIAL("{{$randomProductMaterial}}", "#{Commerce.material}"),
    RANDOM_PRODUCT_NAME("{{$randomProductName}}", "#{Commerce.product_name}"),
    RANDOM_NOUN("{{$randomNoun}}", "#{Hacker.noun}"),
    RANDOM_VERB("{{$randomVerb}}", "#{Hacker.verb}"),
    RANDOM_ING_VERB("{{$randomIngverb}}", "#{Hacker.ing_verb}"),
    RANDOM_ADJECTIVE("{{$randomAdjective}}", "#{Hacker.adjective}"),
    RANDOM_LOREM_WORD("{{$randomLoremWord}}", "#{Lorem.word}"),
    RANDOM_LOREM_WORDS("{{$randomLoremWords}}", "#{Lorem.words}"),
    RANDOM_LOREM_SENTENCE("{{$randomLoremSentence}}", "#{Lorem.sentence}"),
    RANDOM_LOREM_SENTENCES("{{$randomLoremSentences}}", "#{Lorem.sentences '3'}"),
    RANDOM_LOREM_PARAGRAPH("{{$randomLoremParagraph}}", "#{Lorem.paragraph}"),
    RANDOM_LOREM_PARAGRAPHS("{{$randomLoremParagraphs}}", "#{Lorem.paragraphs '3'}");

    private final String placeholder;
    private final String fakerExpression;

    DynamicVariables(String placeholder, String fakerExpression) {
        this.placeholder = placeholder;
        this.fakerExpression = fakerExpression;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getFakerExpression() {
        return fakerExpression;
    }
}
