# struts2-hibernate
A project with Struts2, Hibernate and Hibernate Search

Create JDBC Realm on Wildfly 10
/subsystem=security/security-domain=booksRealm:add(cache-type="default")
/subsystem=security/security-domain=booksRealm/authentication=classic:add(login-modules=[ {code="Database",flag="required",module-options={dsJndiName="java:/jdbc/booksDS",principalsQuery="SELECT passwd FROM users WHERE user_name=?",rolesQuery="SELECT group_name FROM user_groups WHERE user_name = ?", hashAlgorithm="MD5",hashEncoding="BASE64",unauthenticatedIdentity="guest"}},{code="RoleMapping",flag="required",module-options={ rolesProperties="file:${jboss.server.config.dir}/booksRealm.properties", replaceRole="false" }}])
