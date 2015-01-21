package com.bitgrind.meetup.api.options;

/**
 * Supplies parameters common to most Meetup API requests.
 */
public class CommonOptions<T extends CommonOptions> extends ParameterMap {
    /**
     * Selects optional fields or in some cases alters the format of fields in
     * order to request additional detail. Possible values depend on the request
     * being made.
     *
     * @param values one or more top-level field names from the response
     * @return this object for call chaining
     */
    public T fields(String... values) {
        set("fields", csv(values));
        return (T) this;
    }

    /**
     * Specifies a list of fields which should be omitted from the response.
     *
     * @param values one or more top-level field names from the response
     * @return this object for call chaining
     */
    public T omit(String... values) {
        set("omit", csv(values));
        return (T) this;
    }

    /**
     * Specifies the list of fields which should returned in the response. Any
     * fields not in this list will be suppressed.
     *
     * @param values one or more top-level field names from the response
     * @return this object for call chaining
     */
    public T only(String... values) {
        set("only", csv(values));
        return (T) this;
    }

    /**
     * The item limit for requests which return a list of resources. Defaults
     * to 20 if not specified. The number is capped by the Meetup API depending
     * on the type of authentication used.
     * <p/>
     * Always check the field {@link com.bitgrind.meetup.api.model.Meta#count}
     * in the response to see how many items were actually returned for the
     * current page.
     *
     * @param page the maximum number of items to return
     * @return this object for call chaining
     */
    public T page(int page) {
        set("page", page);
        return (T) this;
    }

    /**
     * The page offset to begin at, for requests which return a list of resources.
     * The first item returned will be at (offset * page).
     *
     * @param offset the page offset to start at
     * @return this object for call chaining
     */
    public T offset(int offset) {
        set("offset", offset);
        return (T) this;
    }

    /**
     * Specifies the field to sort on. Only certain fields are supported,
     * depending on the request being made. See the Meetup API documentation
     * for details.
     *
     * @param field the field name to sort on
     * @return this object for call chaining
     */
    public T order(String field) {
        set("order", field);
        return (T) this;
    }

    /**
     * Indicates if the results should be sorted in descending order.
     *
     * @param desc whether results should be in descending order
     * @return this object for call chaining
     */
    public T desc(boolean desc) {
        set("desc", desc);
        return (T) this;
    }
}
