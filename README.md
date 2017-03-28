# cardadapter

利用注解，将model和provider绑定，cardadapter将vh解耦出来（参考了MultiType）

cardadapter支持自动注册和手动注册的方式将model和provider绑定。

方式1：利用注解，在Model中 @CardMap（Provider.class）
方式2：CardAdapter中提供了手动注册的方法 registProvider（Card.class,Provider.class）

Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

    dependencies {
    	        compile 'com.github.DevBigHero:cardadapter:v1.0'
    	}

Step 3. Add the code

    MainActivity.java

    mRvCard.setLayoutManager(new LinearLayoutManager(this));
    adapter = new CardAdapter();
    adapter.setOnItemClick(new CardAdapter.OnItemClick() {
                @Override
                public void OnClick(BaseCard baseCard, int position) {
                    Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void OnLongClick(BaseCard baseCard, int position) {

                }
            });

     for (int i = 0; i < 7; i++) {
                UserCard user = new UserCard("大白" + i);
                card.add(user);
            }
    adapter.addAll(card,true);
    mRvCard.setAdapter(adapter);

    UserCard.java

    @CardMap(UserProvider.class)
    public class UserCard extends BaseCard {
        public String name;

        public UserCard() {
            type = 1;
            sort = 1;
        }

        public UserCard(String name) {
            type = 1;
            sort = 1;
            this.name = name;
        }
    }

    UserProvider.java

    public class UserProvider extends ItemViewProvider<UserCard,UserProvider.VHUser> {


        public UserProvider(CardAdapter.OnItemClick mOnItemClick) {
            super(mOnItemClick);
        }

        @NonNull
        @Override
        public VHUser onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            return new VHUser(inflater.inflate(R.layout.item_text, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VHUser holder, @NonNull UserCard card, @NonNull int position) {
            if (holder instanceof VHUser){
                holder.bind(card,position);
            }
        }


        public class VHUser extends CommonVH<UserCard>{

            @Bind(R.id.tv_name)
            TextView mTvName;

            public VHUser(View itemView) {
                super(itemView);


            }

            @Override
            public void bind(final UserCard t, final int position) {
                mTvName.setText(t.name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClick != null) {
                            mOnItemClick.OnClick(t, position);
                        }
                    }
                });
            }

        }
    }

