package com.bitgrind.meetup.api.options;

/**
 * Created by mrenouf on 1/3/15.
 */
public class GetMembersOptions extends CommonOptions<GetMembersOptions> {

    public GetMembersOptions groupId(int groupId) {
        set("group_id", groupId);
        return this;
    }

    public GetMembersOptions groupId(int groupId, int... others) {
        set("group_id", groupId, others);
        return this;
    }

    public GetMembersOptions groupUrlName(String urlName) {
        set("group_urlname", urlName);
        return this;
    }

    public GetMembersOptions memberId(int memberId) {
        set("member_id", Integer.toString(memberId));
        return this;
    }

    public GetMembersOptions service(String... serviceIdentifiers) {
        put("service", csv(serviceIdentifiers));
        return this;
    }
}
